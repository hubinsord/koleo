package com.example.koleo.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.koleo.data.entities.Station
import com.example.koleo.data.source.local.dao.StationsDao

@Entity(tableName = StationsDao.STATION_TABLE)
class StationDbModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val nameSlug: String,
    val latitude: Double?,
    val longitude: Double?,
    val hits: Int,
    val ibnr: Int?,
    val city: String,
    val region: String,
    val country: String,
    val localizedName: String?,
    val isGroup: Boolean,
    val hasAnnouncements: Boolean,
    val isNearbyStationEnabled: Boolean
)

fun StationDbModel.toStation() = Station(
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

fun Station.toStationDbModel() = StationDbModel(
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
