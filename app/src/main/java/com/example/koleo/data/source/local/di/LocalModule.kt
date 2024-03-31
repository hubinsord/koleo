package com.example.koleo.data.source.local.di

import android.content.Context
import androidx.room.Room
import com.example.koleo.data.source.local.dao.StationsDao
import com.example.koleo.data.source.local.database.StationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideStationDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, StationDatabase::class.java, StationsDao.DB_STATION)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideStationDao(database: StationDatabase) =
        database.getStationDao()


}
