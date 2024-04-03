package com.example.koleo.data.source.remote

import com.example.koleo.data.entities.Station
import com.example.koleo.data.entities.StationKeyword
import com.example.koleo.data.source.remote.api.KoleoApi
import com.example.koleo.data.source.remote.entity.toStation
import com.example.koleo.data.source.remote.entity.toStationKeyword
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.HttpException
import javax.inject.Inject

class StationRemoteDataSource @Inject constructor(
    private val api: KoleoApi,
) {
    fun getStations(): Single<List<Station>> =
        api.getStations()
            .concatMap { stations ->
                Observable.fromIterable(stations)
                    .map { it.toStation() }
                    .toList()
            }

    fun getStationKeywords(): Single<List<StationKeyword>> =
        api.getStationKeywords()
            .concatMap { stationsKeywords ->
                Observable.fromIterable(stationsKeywords)
                    .map { it.toStationKeyword() }
                    .toList()
            }
}
