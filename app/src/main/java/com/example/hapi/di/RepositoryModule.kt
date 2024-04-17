package com.example.hapi.di

import com.example.hapi.data.local.datastore.AuthPreference
import com.example.hapi.data.local.room.dao.history.DetectionHistoryItemDao
import com.example.hapi.data.local.room.dao.history.DiseaseHistoryDao
import com.example.hapi.data.local.room.dao.landowner.LandownerDao
import com.example.hapi.data.local.room.dao.landowner.LandownerDetectionDao
import com.example.hapi.data.local.room.dao.landowner.LandownerDiseaseDao
import com.example.hapi.data.remote.api.AuthApiService
import com.example.hapi.data.remote.api.DetectionApiService
import com.example.hapi.data.remote.api.FarmerApiService
import com.example.hapi.data.remote.api.LandownerApiService
import com.example.hapi.data.repository.AuthRepository
import com.example.hapi.data.repository.DetectionHistoryRepository
import com.example.hapi.data.repository.DetectionRepository
import com.example.hapi.data.repository.FarmerRepository
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
    fun provideLandownerRepository(
        landownerApiService: LandownerApiService,
        authPreference: AuthPreference
    ) = LandownerRepository(
        landownerApiService,
        authPreference
    )

    @Provides
    @Singleton
    fun provideFarmerRepository(
        farmerApiService: FarmerApiService,
        authPreference: AuthPreference
    ) = FarmerRepository(
        farmerApiService,
        authPreference
    )

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApiService: AuthApiService,
        authPreference: AuthPreference,
        landownerDao: LandownerDao
    ) = AuthRepository(
        authApiService,
        authPreference,
        landownerDao
    )

    @Provides
    @Singleton
    fun provideDetectionRepository(
        detectionApiService: DetectionApiService,
        detectionDao: LandownerDetectionDao,
        diseaseDao: LandownerDiseaseDao,
        landownerDao: LandownerDao
    ) = DetectionRepository(
        detectionApiService,
        detectionDao,
        diseaseDao,
        landownerDao
    )

    @Provides
    @Singleton
    fun provideDetectionHistoryRepository(
        detectionHistoryDao: DetectionHistoryItemDao,
        diseaseHistoryDao: DiseaseHistoryDao,
        detectionApiService: DetectionApiService
    ) = DetectionHistoryRepository(
        detectionHistoryDao,
        diseaseHistoryDao,
        detectionApiService
    )
}