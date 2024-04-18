package com.example.hapi.di

import com.example.hapi.data.repository.AuthRepository
import com.example.hapi.data.repository.DetectionHistoryRepository
import com.example.hapi.data.repository.DetectionRepository
import com.example.hapi.domain.usecase.DiseaseDetectionUseCase
import com.example.hapi.domain.usecase.FarmerSignupUseCase
import com.example.hapi.domain.usecase.FetchDetectionHistoryUseCase
import com.example.hapi.domain.usecase.FetchNewestDetectionUseCase
import com.example.hapi.domain.usecase.GetAndSaveDetectionHistoryUseCase
import com.example.hapi.domain.usecase.GetLocalCurrentDetectionUseCase
import com.example.hapi.domain.usecase.GetLocalDetectionUseCase
import com.example.hapi.domain.usecase.GetRemoteDetectionUseCase
import com.example.hapi.domain.usecase.LandownerSignupUseCase
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
    fun provideDiseaseDetectionUseCase(
        detectionRepository: DetectionRepository
    ) = DiseaseDetectionUseCase(detectionRepository)

    @Provides
    @Singleton
    fun provideGetDetectionUseCase(
        detectionRepository: DetectionRepository
    ) = GetLocalCurrentDetectionUseCase(detectionRepository)

    @Provides
    @Singleton
    fun provideGetRemoteDetectionUseCase(
        detectionHistoryRepository: DetectionHistoryRepository
    ) = GetRemoteDetectionUseCase(detectionHistoryRepository)

    @Provides
    @Singleton
    fun provideGetAndSaveDetectionHistoryUseCase(
        detectionHistoryRepository: DetectionHistoryRepository
    ) = GetAndSaveDetectionHistoryUseCase(detectionHistoryRepository)

    @Provides
    @Singleton
    fun provideFetchDetectionHistoryUseCase (
        detectionHistoryRepository: DetectionHistoryRepository
    ) = FetchDetectionHistoryUseCase(detectionHistoryRepository)

    @Provides
    @Singleton
    fun provideFetchNewestDetectionUseCase (
        detectionHistoryRepository: DetectionHistoryRepository
    ) = FetchNewestDetectionUseCase(detectionHistoryRepository)

    @Provides
    @Singleton
    fun provideLocalDetectionUseCase(
        detectionHistoryRepository: DetectionHistoryRepository
    ) = GetLocalDetectionUseCase(detectionHistoryRepository)
}