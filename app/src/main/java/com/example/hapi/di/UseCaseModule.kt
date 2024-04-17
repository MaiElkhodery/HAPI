package com.example.hapi.di

import com.example.hapi.data.repository.AuthRepository
import com.example.hapi.data.repository.DetectionHistoryRepository
import com.example.hapi.data.repository.DetectionRepository
import com.example.hapi.domain.usecase.GetDetectionHistoryListUseCase
import com.example.hapi.domain.usecase.GetRemoteDetectionUseCase
import com.example.hapi.domain.usecase.DiseaseDetectionUseCase
import com.example.hapi.domain.usecase.FarmerSignupUseCase
import com.example.hapi.domain.usecase.GetDetectionUseCase
import com.example.hapi.domain.usecase.LandownerSignupUseCase
import com.example.hapi.domain.usecase.GetRemoteLastDetectionUseCase
import com.example.hapi.domain.usecase.FetchLastFiveDetectionsUseCase
import com.example.hapi.domain.usecase.GetSavedLastFiveDetectionsUseCase
import com.example.hapi.domain.usecase.SigninUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideLandownerSignupUseCase(
        authRepository: AuthRepository
    ) = LandownerSignupUseCase(authRepository)

    @Provides
    @Singleton
    fun provideSignupUseCase(
        authRepository: AuthRepository
    ) = FarmerSignupUseCase(authRepository)

    @Provides
    @Singleton
    fun provideSigninUseCase(
        authRepository: AuthRepository
    ) = SigninUseCase(authRepository)


    @Provides
    @Singleton
    fun provideDetectionUseCase(
        detectionRepository: DetectionRepository
    ) = DiseaseDetectionUseCase(detectionRepository)

    @Provides
    @Singleton
    fun provideGetDetectionUseCase(
        detectionRepository: DetectionRepository
    ) = GetDetectionUseCase(detectionRepository)

    @Provides
    @Singleton
    fun provideGetDetectionItemUseCase(
        detectionHistoryRepository: DetectionHistoryRepository
    ) = GetRemoteDetectionUseCase(detectionHistoryRepository)

    @Provides
    @Singleton
    fun provideDetectionHistoryUseCase(
        detectionHistoryRepository: DetectionHistoryRepository
    ) = GetDetectionHistoryListUseCase(detectionHistoryRepository)

    @Provides
    @Singleton
    fun provideLastDetectionUseCase(
        detectionHistoryRepository: DetectionHistoryRepository
    ) = GetRemoteLastDetectionUseCase(detectionHistoryRepository)

    @Provides
    @Singleton
    fun provideLastFiveDetectionsUseCase(
        detectionHistoryRepository: DetectionHistoryRepository
    ) = FetchLastFiveDetectionsUseCase(detectionHistoryRepository)

    @Provides
    @Singleton
    fun provideLocalDetectionsUseCase(
        detectionHistoryRepository: DetectionHistoryRepository
    ) = GetSavedLastFiveDetectionsUseCase(detectionHistoryRepository)
}