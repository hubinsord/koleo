package com.example.koleo.domain.usecase

import com.example.koleo.data.StationRepository
import com.example.koleo.data.entities.StationKeyword
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetStationKeywordsUseCase @Inject constructor(
    private val stationRepository: StationRepository
) {
    operator fun invoke(): Single<List<StationKeyword>> = stationRepository.getStationKeywords()
}