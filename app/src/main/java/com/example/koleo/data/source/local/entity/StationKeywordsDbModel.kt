package com.example.koleo.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.koleo.data.entities.StationKeywords
import com.example.koleo.data.source.local.dao.StationsDao

@Entity(tableName = StationsDao.STATION_KEYWORDS_TABLE)
data class StationKeywordsDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val keyword: String,
    val stationId: Int
)

fun StationKeywordsDbModel.toStationKeywords() = StationKeywords(
    id = id,
    keyword = keyword,
    stationId = stationId
)

fun StationKeywords.toStationKeywordsDbModel() = StationKeywordsDbModel(
    id = id,
    keyword = keyword,
    stationId = stationId
)
