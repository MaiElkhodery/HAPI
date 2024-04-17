package com.example.hapi.di

import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.local.room.dao.current_detection.CurrentDiseaseDao
import com.example.hapi.data.local.room.dao.current_detection.CurrrentDetectionDao
import com.example.hapi.data.local.room.dao.history.DetectionDao
import com.example.hapi.data.local.room.dao.history.DiseaseDao
import com.example.hapi.data.remote.api.AuthApiService
import com.example.hapi.data.remote.api.DetectionApiService
import com.example.hapi.data.repository.AuthRepository
import com.example.hapi.data.repository.DetectionHistoryRepository
import com.example.hapi.data.repository.DetectionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApiService: AuthApiService,
        authPreference: UserDataPreference,
    ) = AuthRepository(
        authApiService,
        authPreference,
    )

    @Provides
    @Singleton
    fun provideDetectionRepository(
        detectionApiService: DetectionApiService,
        detectionDao: CurrrentDetectionDao,
        diseaseDao: CurrentDiseaseDao,
    ) = DetectionRepository(
        detectionApiService,
        detectionDao,
        diseaseDao
    )

    @Provides
    @Singleton
    fun provideDetectionHistoryRepository(
        detectionHistoryDao: DetectionDao,
        diseaseHistoryDao: DiseaseDao,
        detectionApiService: DetectionApiService
    ) = DetectionHistoryRepository(
        detectionHistoryDao,
        diseaseHistoryDao,
        detectionApiService
    )
}