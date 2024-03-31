package com.example.koleo.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.koleo.data.source.local.dao.StationsDao
import com.example.koleo.data.source.local.entity.StationDbModel

@Database(entities = [StationDbModel::class], version = 1)
abstract class StationDatabase: RoomDatabase() {
    abstract fun getStationDao(): StationsDao
}
