package com.example.koleo.data.source.remote.entity

import com.example.koleo.data.entities.Station
import com.squareup.moshi.Json

data class StationResponseModel(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "name_slug") val nameSlug: String,
    @Json(name = "latitude") val latitude: Double?,
    @Json(name = "longitude") val longitude: Double?,
    @Json(name = "hits") val hits: Int,
    @Json(name = "ibnr") val ibnr: Int?,
    @Json(name = "city") val city: String,
    @Json(name = "region") val region: String,
    @Json(name = "country") val country: String,
    @Json(name = "localised_name") val localizedName: Any?,
    @Json(name = "is_group") val isGroup: Boolean,
    @Json(name = "has_announcements") val hasAnnouncements: Boolean,
    @Json(name = "is_nearby_station_enabled") val isNearbyStationEnabled: Boolean
)

fun StationResponseModel.toStation(): Station = Station(
    id = id,
    name = name,
    nameSlug = nameSlug,
    latitude = latitude,
    longitude = longitude,
    hits = hits,
    ibnr = ibnr,
    city = city,
    region = region,
    country = country,
    localizedName = localizedName,
    isGroup = isGroup,
    hasAnnouncements = hasAnnouncements,
    isNearbyStationEnabled = isNearbyStationEnabled
)
