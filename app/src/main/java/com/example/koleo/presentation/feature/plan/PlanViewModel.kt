package com.example.koleo.presentation.feature.plan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koleo.data.StationRepository
import com.example.koleo.data.entities.Station
import com.example.koleo.data.entities.StationKeyword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx3.asFlow
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class PlanViewModel @Inject constructor(
    private val repository: StationRepository,
) : ViewModel() {

    init {
        fetchStations()
        fetchStationKeywords()
    }

    private var stations = listOf<Station>()
    private var keywords = listOf<StationKeyword>()

    private val _state = MutableStateFlow<PlanState>(PlanState.Departure(emptyList()))
    val state: StateFlow<PlanState> = _state.asStateFlow()

    private val _event = Channel<PlanEvent>()
    val event = _event.receiveAsFlow().distinctUntilChanged()

    private val _departure = MutableStateFlow<Station?>(null)
    val departure = _departure.asStateFlow().filterNotNull()
    private val _arrival = MutableStateFlow<Station?>(null)
    val arrival = _arrival.asStateFlow().filterNotNull()

    private fun fetchStations() {
        viewModelScope.launch {
            repository.getStations().toObservable()
                .asFlow()
                .catch {
                    if (it is HttpException && it.code() == 504)
                        _event.send(PlanEvent.ShowError)
                }
                .collect { stations = it }
        }
    }

    private fun fetchStationKeywords() {
        viewModelScope.launch {
            repository.getStationKeywords().toObservable()
                .asFlow()
                .catch {
                    if (it is HttpException && it.code() == 504)
                        _event.send(PlanEvent.ShowError)
                }
                .collect { keywords = it }
        }
    }

    fun departureInputChanged(input: String) {
        viewModelScope.launch { _state.emit(PlanState.Departure(getStationsFromInput(input))) }
    }

    fun stationSelected(station: Station) {
        when (state.value) {
            is PlanState.Departure -> departureStationSelected(station)
            is PlanState.Arrival -> arrivalStationSelected(station)
        }
    }

    fun arrivalInputChanged(input: String) {
        viewModelScope.launch { _state.emit(PlanState.Arrival(getStationsFromInput(input))) }
    }

    private fun departureStationSelected(station: Station) {
        viewModelScope.launch {
            _departure.emit(station)
            _state.emit(PlanState.Arrival(emptyList()))
            _event.send(PlanEvent.ShowArrival)
        }
    }

    private fun arrivalStationSelected(station: Station) {
        viewModelScope.launch {
            _arrival.emit(station)
            _departure.value?.let { departure ->
                _arrival.value?.let { arrival ->
                    _event.send(PlanEvent.ShowDistanceScreen(departure, arrival))
                }
            }
        }
    }

    private fun getStationsFromInput(input: String): List<Station> {
        if (input.length < 3) return emptyList()
        val matchingIds = getMatchingIds(input)
        return stations
            .filter { station -> matchingIds.contains(station.id) }
            .sortedByDescending { it.hits }
    }

    private fun getMatchingIds(input: String) = keywords
        .filter { it.keyword.contains(input) }
        .map { it.stationId }
}