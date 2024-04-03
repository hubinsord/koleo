package com.example.koleo.presentation.feature.distance

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.koleo.data.entities.Station
import com.example.koleo.domain.usecase.CalculateDistanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DistanceViewModel @Inject constructor(
    state: SavedStateHandle,
    private val calculateDistanceUseCase: CalculateDistanceUseCase,
) : ViewModel() {

    private val departureArgs = state.get<Station>(ARG_DEPARTURE_STATION)
    private val arrivalArgs = state.get<Station>(ARG_ARRIVAL_STATION)
    val departureName
        get() = departureArgs?.name
    val arrivalName
        get() = arrivalArgs?.name
    val departureLatitude
        get() = departureArgs?.latitude ?: 0.0
    val departureLongitude
        get() = departureArgs?.longitude ?: 0.0
    val arrivalLatitude
        get() = arrivalArgs?.latitude ?: 0.0
    val arrivalLongitude
        get() = arrivalArgs?.longitude ?: 0.0
    val distance
        get() = calculateDistanceUseCase(
            departureLatitude,
            departureLongitude,
            arrivalLatitude,
            arrivalLongitude,
        )

    companion object {
        private const val ARG_DEPARTURE_STATION = "departureStation"
        private const val ARG_ARRIVAL_STATION = "arrivalStation"
    }
}