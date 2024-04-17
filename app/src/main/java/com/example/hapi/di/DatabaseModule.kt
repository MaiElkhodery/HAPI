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
    fun provideLandownerDao(database: HapiDatabase) = database.landownerDao()

    @Singleton
    @Provides
    fun provideLandownerDiseaseDao(database: HapiDatabase) = database.landownerDiseaseDao()

    @Singleton
    @Provides
    fun provideLandownerDetectionDao(database: HapiDatabase) = database.landownerDetectionDao()

    @Singleton
    @Provides
    fun provideDetectionDetailsDao(database: HapiDatabase) = database.detectionDetailsDao()

    @Singleton
    @Provides
    fun provideDiseaseDetailsDao(database: HapiDatabase) = database.diseaseDetailsDao()

    @Singleton
    @Provides
    fun provideDetectionHistoryItemDao(database: HapiDatabase) = database.detectionHistoryItemDao()

    @Singleton
    @Provides
    fun provideDiseaseHistoryDao(database: HapiDatabase) = database.diseaseHistoryDao()

}