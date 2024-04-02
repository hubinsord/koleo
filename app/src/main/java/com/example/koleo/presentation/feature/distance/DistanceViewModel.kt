package com.example.koleo.presentation.feature.distance

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.koleo.data.entities.Station
import com.example.koleo.domain.usecase.CalculateDistanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

@HiltViewModel
class DistanceViewModel @Inject constructor(
    state: SavedStateHandle,
    private val calculateDistanceUseCase: CalculateDistanceUseCase,
) : ViewModel() {

    private val departureArgs = state.get<Station>(ARG_DEPARTURE_STATION)
    private val arrivalArgs = state.get<Station>(ARG_ARRIVAL_STATION)

    private val _departure = MutableStateFlow(departureArgs)
    val departure = _departure.asStateFlow().filterNotNull()

    private val _arrival = MutableStateFlow(arrivalArgs)
    val arrival = _arrival.asStateFlow().filterNotNull()

    val distance
        get() = calculateDistanceUseCase(
            departureArgs?.latitude ?: 0.00,
            departureArgs?.longitude ?: 0.00,
            arrivalArgs?.latitude ?: 0.00,
            arrivalArgs?.longitude ?: 0.00
        )

    companion object {
        private const val ARG_DEPARTURE_STATION = "departureStation"
        private const val ARG_ARRIVAL_STATION = "arrivalStation"
    }
}