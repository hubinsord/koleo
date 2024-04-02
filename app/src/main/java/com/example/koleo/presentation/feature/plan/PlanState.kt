package com.example.koleo.presentation.feature.plan

import com.example.koleo.data.entities.Station

sealed class PlanState(val items: List<Station>) {
    class Departure(items: List<Station>) : PlanState(items)
    class Arrival(items: List<Station>) : PlanState(items)
}

sealed interface PlanEvent {
    data object ShowDeparture : PlanEvent
    data object ShowArrival : PlanEvent
    data class ShowDistanceScreen(val departureStation: Station, val arrivalStation: Station) : PlanEvent
}