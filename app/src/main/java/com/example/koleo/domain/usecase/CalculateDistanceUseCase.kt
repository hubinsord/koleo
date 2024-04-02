package com.example.koleo.domain.usecase

import javax.inject.Inject
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

private const val EARTH_RADIUS_KM = 6371.0

class CalculateDistanceUseCase @Inject constructor() {
    operator fun invoke(
        latitude1: Double,
        longitude1: Double,
        latitude2: Double,
        longitude2: Double,
    ): Double {
        val dLat = Math.toRadians(latitude2 - latitude1)
        val dLon = Math.toRadians(longitude2 - longitude1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(latitude1)) * cos(Math.toRadians(latitude2)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return EARTH_RADIUS_KM * c
    }
}