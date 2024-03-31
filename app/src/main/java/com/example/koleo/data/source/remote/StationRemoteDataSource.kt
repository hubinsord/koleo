package com.example.koleo.data.source.remote

import com.example.koleo.data.entities.Station
import com.example.koleo.data.entities.StationKeywords
import com.example.koleo.data.source.remote.api.KoleoApi
import com.example.koleo.data.source.remote.entity.toStation
import com.example.koleo.data.source.remote.entity.toStationKeywords
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class StationRemoteDataSource @Inject constructor(
    private val api: KoleoApi
) {
    fun getStations(): Single<List<Station>> =
        api.getStations().concatMap { stations ->
            Observable.fromIterable(stations)
                .map { it.toStation() }
                .toList()
        }

    fun getStationKeywords(): Single<List<StationKeywords>> =
        api.getStationKeywords().concatMap { stationsKeywords ->
            Observable.fromIterable(stationsKeywords)
                .map { it.toStationKeywords()}
                .toList()
        }
}
