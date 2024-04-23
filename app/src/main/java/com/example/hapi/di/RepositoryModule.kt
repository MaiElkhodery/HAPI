package com.example.hapi.di

import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.local.room.dao.current_detection.CurrentDetectionDao
import com.example.hapi.data.local.room.dao.current_detection.CurrentDiseaseDao
import com.example.hapi.data.local.room.dao.detection_history.DetectionOfHistoryDao
import com.example.hapi.data.local.room.dao.land_history.LandDataDao
import com.example.hapi.data.remote.api.AuthApiService
import com.example.hapi.data.remote.api.DetectionApiService
import com.example.hapi.data.remote.api.LandownerApiService
import com.example.hapi.data.repository.AuthRepository
import com.example.hapi.data.repository.DetectionHistoryRepository
import com.example.hapi.data.repository.DetectionRepository
import com.example.hapi.data.repository.LandownerRepository
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
        detectionDao: CurrentDetectionDao,
        diseaseDao: CurrentDiseaseDao,
        userDataPreference: UserDataPreference
    ) = DetectionRepository(
        detectionApiService,
        detectionDao,
        diseaseDao,
        userDataPreference
    )

    @Provides
    @Singleton
    fun provideDetectionHistoryRepository(
        detectionOfHistoryDao: DetectionOfHistoryDao,
        detectionApiService: DetectionApiService,
    ) = DetectionHistoryRepository(
        detectionApiService,
        detectionOfHistoryDao
    )

    @Provides
    @Singleton
    fun provideLandownerRepository(
        landownerApiService: LandownerApiService,
        authPreference: UserDataPreference,
        landDataDao: LandDataDao
    ) = LandownerRepository(
        landownerApiService,
        authPreference,
        landDataDao
    )
}