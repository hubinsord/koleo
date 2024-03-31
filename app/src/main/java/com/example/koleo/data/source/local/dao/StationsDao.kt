package com.example.koleo.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.koleo.data.source.local.entity.StationDbModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface StationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStations(stations: List<StationDbModel>) : Completable

    @Query("DELETE FROM $STATION_TABLE")
    fun deleteStations() : Completable

    @Query("SELECT * FROM $STATION_TABLE WHERE id = :id")
    fun getStationById(id: Int): Single<StationDbModel>

    companion object {
        const val DB_STATION = "STATION_DATABASE"
        const val STATION_TABLE = "STATION_TABLE"
        const val STATION_KEYWORDS_TABLE = "STATION_KEYWORDS_TABLE"
    }
}
