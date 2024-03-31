package com.example.koleo.data.source.remote.api

import com.example.koleo.data.source.remote.entity.StationKeywordsResponseModel
import com.example.koleo.data.source.remote.entity.StationResponseModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface KoleoApi {

    @GET("stations")
    fun getStations(): Single<List<StationResponseModel>>

    @GET("station_keywords")
    fun getStationKeywords(): Single<List<StationKeywordsResponseModel>>

    companion object {
        const val BASE_URL = "https://koleo.pl/api/v2/main/"
    }
}
