package com.example.koleo.data.source.remote.entity

import com.example.koleo.data.entities.Station
import com.squareup.moshi.Json

data class StationResponseModel(
    @Json(name = "city")
    val city: String,
    @Json(name = "country")
    val country: String,
    @Json(name = "has_announcements")
    val hasAnnouncements: Boolean,
    @Json(name = "hits")
    val hits: Int,
    @Json(name = "ibnr")
    val ibnr: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "is_group")
    val isGroup: Boolean,
    @Json(name = "is_nearby_station_enabled")
    val isNearbyStationEnabled: Boolean,
    @Json(name = "latitude")
    val latitude: Double,
    @Json(name = "localised_name")
    val localizedName: String?,
    @Json(name = "longitude")
    val longitude: Double,
    @Json(name = "name")
    val name: String,
    @Json(name = "name_slug")
    val nameSlug: String,
    @Json(name = "region")
    val region: String
)

fun StationResponseModel.toStation(): Station = Station(
    city = city,
    country = country,
    hasAnnouncements = hasAnnouncements,
    hits = hits,
    ibnr = ibnr,
    id = id,
    isGroup = isGroup,
    isNearbyStationEnabled = isNearbyStationEnabled,
    latitude = latitude,
    localizedName = localizedName ?: "",
    longitude = longitude,
    name = name,
    nameSlug = nameSlug,
    region = region
)
