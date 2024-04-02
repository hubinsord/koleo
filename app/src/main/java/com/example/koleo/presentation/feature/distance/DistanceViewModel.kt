package com.example.koleo.presentation.feature.distance

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.koleo.data.entities.Station
import com.example.koleo.domain.usecase.CalculateDistanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class DistanceViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val calculateDistanceUseCase: CalculateDistanceUseCase,
) : ViewModel() {

    private val departureArgs = state.get<Station>("departureStation")
    val arrivalArgs = state.get<Station>("arrivalStation")

    private val _departure = MutableStateFlow<Station?>(departureArgs)
    val departure = _departure.asStateFlow().filterNotNull()

    val distance = flow<Double> {
         val calc = calculateDistanceUseCase(
            departureArgs?.latitude ?: 0.00,
            departureArgs?.longitude ?: 0.00,
            arrivalArgs?.latitude ?: 0.00,
            arrivalArgs?.longitude ?: 0.00
        )
        emit(calc)
    }
}