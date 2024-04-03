package com.example.koleo.data

import com.example.koleo.data.entities.Station
import com.example.koleo.data.entities.StationKeyword
import com.example.koleo.data.source.local.StationLocalDataSource
import com.example.koleo.data.source.remote.StationRemoteDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class StationRepository @Inject constructor(
    private val remoteDataSource: StationRemoteDataSource,
    private val localDataSource: StationLocalDataSource
) {
    fun getStations(): Single<List<Station>> =
        remoteDataSource.getStations()

    fun getStationKeywords(): Single<List<StationKeyword>> =
        remoteDataSource.getStationKeywords()
}
