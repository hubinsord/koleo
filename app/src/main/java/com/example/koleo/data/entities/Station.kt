package com.example.koleo.data.entities

data class Station(
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
