package com.example.koleo.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.koleo.data.entities.StationKeyword
import com.example.koleo.data.source.local.dao.StationsDao

@Entity(tableName = StationsDao.STATION_KEYWORDS_TABLE)
data class StationKeywordDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val keyword: String,
    val stationId: Int
)

fun StationKeywordDbEntity.toStationKeyword() = StationKeyword(
    id = id,
    keyword = keyword,
    stationId = stationId
)

fun StationKeyword.toStationKeywordDbEntity() = StationKeywordDbEntity(
    id = id,
    keyword = keyword,
    stationId = stationId
)
