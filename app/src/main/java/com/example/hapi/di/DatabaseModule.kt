package com.example.hapi.di

import android.content.Context
import androidx.room.Room
import com.example.hapi.data.local.room.db.HapiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): HapiDatabase {
        return Room.databaseBuilder(
            context,
            HapiDatabase::class.java,
            "hapi_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCurrentDiseaseDao(database: HapiDatabase) = database.currentDiseaseDao()

    @Singleton
    @Provides
    fun provideCurrentDetectionDao(database: HapiDatabase) = database.currentDetectionDao()

    @Singleton
    @Provides
    fun provideDetectionOfHistoryDao(database: HapiDatabase) = database.detectionOfHistoryDao()

    @Singleton
    @Provides
    fun provideLandDataDao(database: HapiDatabase) = database.landDataDao()

    @Singleton
    @Provides
    fun provideFarmerDao(database: HapiDatabase) = database.farmerDao()

}