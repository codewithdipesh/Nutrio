package com.codewithdipesh.mylibrary.di

import android.app.Application
import androidx.room.Room
import com.codewithdipesh.mylibrary.local.TrackerDatabase
import com.codewithdipesh.mylibrary.remote.ApiConstants
import com.codewithdipesh.mylibrary.remote.OpenFoodApi
import com.codewithdipesh.mylibrary.repository.TrackerRepositoryImpl
import com.codewithdipesh.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient( client: OkHttpClient):OpenFoodApi{
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(OpenFoodApi::class.java)
    }
    @Provides
    @Singleton
    fun provideTrackerDatabase(app : Application):TrackerDatabase{
        return  Room.databaseBuilder(
            app,
            TrackerDatabase::class.java,
            "tracker_db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideTrackerRepository(
        api: OpenFoodApi,
        db: TrackerDatabase
    ): TrackerRepository = TrackerRepositoryImpl(
        dao = db.dao,
        api = api
    )


}