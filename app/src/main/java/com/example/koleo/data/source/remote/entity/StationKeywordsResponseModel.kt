package com.example.koleo.data.source.remote.entity

import com.example.koleo.data.entities.StationKeywords
import com.squareup.moshi.Json

data class StationKeywordsResponseModel(
    @Json(name = "id") val id: Int,
    @Json(name = "keyword") val keyword: String,
    @Json(name = "station_id") val stationId: Int
)

fun StationKeywordsResponseModel.toStationKeywords() = StationKeywords(
    id = id,
    keyword = keyword,
    stationId = stationId
)
