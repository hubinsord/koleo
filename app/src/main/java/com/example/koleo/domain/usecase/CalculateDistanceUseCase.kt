package com.example.koleo.domain.usecase

import android.location.Location
import javax.inject.Inject


class CalculateDistanceUseCase @Inject constructor() {
    operator fun invoke(
        latitude1: Double,
        longitude1: Double,
        latitude2: Double,
        longitude2: Double,
    ) = distanceInMeter(latitude1, longitude1, latitude2, longitude2)

    private fun distanceInMeter(
        startLatitude: Double,
        startLongitude: Double,
        endLatitude: Double,
        endLongitude: Double,
    ): Double {
        val results = FloatArray(1)
        Location.distanceBetween(startLatitude, startLongitude, endLatitude, endLongitude, results)
        return results[0].toDouble()
    }
}