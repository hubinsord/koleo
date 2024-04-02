package com.example.koleo.presentation.feature.plan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koleo.data.entities.Station
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PlanViewModel : ViewModel() {
    private val stations = listOf(
        Station(
            0,
            "Garbary",
            "Garbary",
            30.456,
            30.123,
            0,
            null,
            "Poznan",
            "",
            "",
            "",
            false,
            false,
            false
        ),
        Station(
            1,
            "Ogrody",
            "Ogrody",
            30.456,
            30.123,
            0,
            null,
            "Poznan",
            "",
            "",
            "",
            false,
            false,
            false
        ),
        Station(
            2,
            "Wilda",
            "Wilda",
            30.456,
            30.123,
            0,
            null,
            "Poznan",
            "",
            "",
            "",
            false,
            false,
            false
        ),
        Station(
            3,
            "Wilczak",
            "Wilczak",
            30.456,
            30.123,
            0,
            null,
            "Poznan",
            "",
            "",
            "",
            false,
            false,
            false
        ),
        Station(
            4,
            "Pestka",
            "Pestka",
            30.456,
            30.123,
            0,
            null,
            "Poznan",
            "",
            "",
            "",
            false,
            false,
            false
        ),
        Station(
            5,
            "Grunwald",
            "Grunwald",
            30.456,
            30.123,
            0,
            null,
            "Poznan",
            "",
            "",
            "",
            false,
            false,
            false
        ),
    )

    private val _state = MutableStateFlow<PlanState>(PlanState.Departure(emptyList()))
    val state: StateFlow<PlanState> = _state.asStateFlow()

    private val _event = Channel<PlanEvent>()
    val event = _event.receiveAsFlow()

    private val _departure = MutableStateFlow<Station?>(null)
    val departure = _departure.asStateFlow().filterNotNull()
    private val _arrival = MutableStateFlow<Station?>(null)
    val arrival = _arrival.asStateFlow().filterNotNull()

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
        return stations.filter { it.name.contains(input) }
    }
}