package com.example.koleo.domain.usecase

import com.example.koleo.data.StationRepository
import com.example.koleo.data.entities.Station
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetStationsUseCase @Inject constructor(
    private val stationRepository: StationRepository
) {
    operator fun invoke(): Single<List<Station>> = stationRepository.getStations()
}