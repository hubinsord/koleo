package com.example.koleo.data.source.remote.di

import android.content.Context
import com.example.koleo.data.source.remote.interceptor.CacheInterceptor
import com.example.koleo.data.source.remote.interceptor.ForceCacheInterceptor
import com.example.koleo.data.source.remote.api.KoleoApi
import com.example.koleo.data.source.remote.interceptor.HeaderInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDiModule {
    private const val CACHE_SIZE = 10L * 1024L * 1024L
    private const val READ_TIMEOUT_SECONDS = 20L
    private const val CONNECT_TIMEOUT_SECONDS = 20L

    @Singleton
    @Provides
    fun provideKoleoApi(retrofit: Retrofit): KoleoApi =
        retrofit.create(KoleoApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(
        moshiConverterFactory: MoshiConverterFactory,
        client: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(KoleoApi.BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        )

    @Singleton
    @Provides
    fun provideHttpClient(@ApplicationContext context: Context) = OkHttpClient.Builder()
        .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .cache(Cache(context.cacheDir, CACHE_SIZE))
        .addNetworkInterceptor(CacheInterceptor())
        .addInterceptor(ForceCacheInterceptor(context))
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(HeaderInterceptor())
        .build()
}