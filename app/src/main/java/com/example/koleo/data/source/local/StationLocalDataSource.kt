package com.example.koleo.data.source.local

import com.example.koleo.data.entities.Station
import com.example.koleo.data.entities.StationKeyword
import com.example.koleo.data.source.local.database.StationDatabase
import com.example.koleo.data.source.local.entity.toStation
import com.example.koleo.data.source.local.entity.toStationDbModel
import com.example.koleo.data.source.local.entity.toStationKeyword
import com.example.koleo.data.source.local.entity.toStationKeywordDbEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class StationLocalDataSource @Inject constructor(
    private val stationDatabase: StationDatabase,
) {
    private val stationDao = stationDatabase.getStationDao()

    fun saveStations(stations: List<Station>) =
        stationDao.insertStations(stations.map { it.toStationDbModel() })

    fun deleteStations() = stationDao.deleteStations()

    fun getStationById(id: Int): Single<Station> =
        stationDao.getStationById(id)
            .map { it.toStation() }

    fun saveStationKeywords(keywords: List<StationKeyword>) =
        stationDao.insertStationKeywords(keywords.map { it.toStationKeywordDbEntity() })

    fun getStationByName(searchQuery: String): Single<List<StationKeyword>> =
        stationDao.getStationByName(searchQuery)
            .map { stationKeywordsDbModel ->
                stationKeywordsDbModel.map { it.toStationKeyword() }
            }
}
