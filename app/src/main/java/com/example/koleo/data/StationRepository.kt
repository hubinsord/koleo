package com.example.koleo.data

import com.example.koleo.data.entities.Station
import com.example.koleo.data.source.remote.StationRemoteDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class StationRepository @Inject constructor(
    private val remoteDataSource: StationRemoteDataSource
) {
    fun getStations(): Single<List<Station>> =
        remoteDataSource.getStations()
}