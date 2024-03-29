package com.example.koleo.data.entities

data class Station(
    val city: String,
    val country: String,
    val hasAnnouncements: Boolean,
    val hits: Int,
    val ibnr: Int,
    val id: Int,
    val isGroup: Boolean,
    val isNearbyStationEnabled: Boolean,
    val latitude: Double,
    val localizedName: String?,
    val longitude: Double,
    val name: String,
    val nameSlug: String,
    val region: String
)