package com.example.koleo.data.source.remote.entity

import com.example.koleo.data.entities.StationKeyword
import com.squareup.moshi.Json

data class StationKeywordResponseEntity(
    @Json(name = "id") val id: Int,
    @Json(name = "keyword") val keyword: String,
    @Json(name = "station_id") val stationId: Int
)

fun StationKeywordResponseEntity.toStationKeyword() = StationKeyword(
    id = id,
    keyword = keyword,
    stationId = stationId
)
