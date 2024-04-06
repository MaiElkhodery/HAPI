package com.example.hapi.di

import com.example.hapi.data.repository.AuthRepository
import com.example.hapi.data.repository.LandownerRepository
import com.example.hapi.domain.usecase.CropRecommendationUseCase
import com.example.hapi.domain.usecase.FarmerSignupUseCase
import com.example.hapi.domain.usecase.LandownerSignupUseCase
import com.example.hapi.domain.usecase.SigninUseCase
import com.example.hapi.domain.usecase.UploadSelectedCropUseCase
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
    fun provideCropRecommendationUseCase(
        landownerRepository: LandownerRepository
    ) = CropRecommendationUseCase(landownerRepository)

    @Provides
    @Singleton
    fun provideUploadSelectedCropUseCase(
        landownerRepository: LandownerRepository
    ) = UploadSelectedCropUseCase(landownerRepository)

}