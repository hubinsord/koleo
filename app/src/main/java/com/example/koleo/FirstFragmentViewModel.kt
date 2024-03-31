package com.example.koleo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koleo.domain.usecase.GetStationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.rx3.asFlow
import javax.inject.Inject

@HiltViewModel
class FirstFragmentViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase
) : ViewModel() {

    val stations = getStationsUseCase().toObservable()
        .asFlow()
        .flowOn(Dispatchers.IO)
}
