package com.example.koleo.data.source.remote.api

import com.example.koleo.data.source.remote.entity.StationKeywordResponseEntity
import com.example.koleo.data.source.remote.entity.StationResponseEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface KoleoApi {

    @GET("stations")
    fun getStations(): Single<List<StationResponseEntity>>

    @GET("station_keywords")
    fun getStationKeywords(): Single<List<StationKeywordResponseEntity>>

    companion object {
        const val BASE_URL = "https://koleo.pl/api/v2/main/"
    }
}
