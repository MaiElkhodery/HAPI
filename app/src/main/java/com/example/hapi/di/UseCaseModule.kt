package com.example.hapi.di

import com.example.hapi.data.repository.AuthRepository
import com.example.hapi.data.repository.DetectionHistoryRepository
import com.example.hapi.data.repository.DiseaseDetectionRepository
import com.example.hapi.data.repository.LandRepository
import com.example.hapi.data.repository.LandownerRepository
import com.example.hapi.domain.usecase.detection.DeleteDetectionHistoryUseCase
import com.example.hapi.domain.usecase.detection.DetectDiseaseUseCase
import com.example.hapi.domain.usecase.detection.FetchDetectionHistoryUseCase
import com.example.hapi.domain.usecase.detection.GetCurrentDetectionUseCase
import com.example.hapi.domain.usecase.detection.GetDetectionDetailsUseCase
import com.example.hapi.domain.usecase.detection.GetDetectionHistoryByUsernameUseCase
import com.example.hapi.domain.usecase.detection.GetDetectionHistoryUseCase
import com.example.hapi.domain.usecase.detection.GetLastDetectionUseCase
import com.example.hapi.domain.usecase.land.DeleteLandHistoryUseCase
import com.example.hapi.domain.usecase.land.FetchLandHistoryUseCase
import com.example.hapi.domain.usecase.land.GetLandHistoryByActionTypeUseCase
import com.example.hapi.domain.usecase.land.GetLandHistoryUseCase
import com.example.hapi.domain.usecase.land.GetLastLandHistoryItemUseCase
import com.example.hapi.domain.usecase.landowner.CropRecommendationUseCase
import com.example.hapi.domain.usecase.landowner.FetchFarmersUseCase
import com.example.hapi.domain.usecase.landowner.FetchTanksDataUseCase
import com.example.hapi.domain.usecase.landowner.GetFarmersUseCase
import com.example.hapi.domain.usecase.landowner.GetLastFarmerUseCase
import com.example.hapi.domain.usecase.landowner.UploadSelectedCropUseCase
import com.example.hapi.domain.usecase.sign.CheckPasswordUseCase
import com.example.hapi.domain.usecase.sign.DeleteAccountUseCase
import com.example.hapi.domain.usecase.sign.FarmerSignupUseCase
import com.example.hapi.domain.usecase.sign.LandownerSignupUseCase
import com.example.hapi.domain.usecase.sign.LogoutUseCase
import com.example.hapi.domain.usecase.sign.SigninUseCase
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


    @Provides
    @Singleton
    fun provideDetectDiseaseUseCase(
        detectionRepository: DiseaseDetectionRepository
    ) = DetectDiseaseUseCase(detectionRepository)

    @Provides
    @Singleton
    fun provideGetCurrentDetectionUseCase(
        detectionRepository: DiseaseDetectionRepository
    ) = GetCurrentDetectionUseCase(detectionRepository)

    @Provides
    @Singleton
    fun provideGetDetectionDetailsUseCase(
        detectionHistoryRepository: DetectionHistoryRepository
    ) = GetDetectionDetailsUseCase(detectionHistoryRepository)

    @Provides
    @Singleton
    fun provideGetAndSaveDetectionHistoryUseCase(
        detectionHistoryRepository: DetectionHistoryRepository
    ) = FetchDetectionHistoryUseCase(detectionHistoryRepository)

    @Provides
    @Singleton
    fun provideGetDetectionHistoryUseCase(
        detectionHistoryRepository: DetectionHistoryRepository
    ) = GetDetectionHistoryUseCase(detectionHistoryRepository)

    @Provides
    @Singleton
    fun provideFetchNewestDetectionUseCase(
        detectionHistoryRepository: DetectionHistoryRepository
    ) = GetLastDetectionUseCase(detectionHistoryRepository)

    @Provides
    @Singleton
    fun provideFetchLandHistoryUseCase(
        landRepository: LandRepository
    ) = FetchLandHistoryUseCase(landRepository)

    @Provides
    @Singleton
    fun provideGetLastLandHistoryItemUseCase(
        landRepository: LandRepository
    ) = GetLastLandHistoryItemUseCase(landRepository)

    @Provides
    @Singleton
    fun provideGetLandHistoryUseCase(
        landRepository: LandRepository
    ) = GetLandHistoryUseCase(landRepository)

    @Provides
    @Singleton
    fun provideFetchTanksDataUseCase(
        landownerRepository: LandownerRepository
    ) = FetchTanksDataUseCase(landownerRepository)

    @Provides
    @Singleton
    fun provideGetLastFarmerUseCase(
        landownerRepository: LandownerRepository
    ) = GetLastFarmerUseCase(landownerRepository)

    @Provides
    @Singleton
    fun provideFetchFarmersUseCase(
        landownerRepository: LandownerRepository
    ) = FetchFarmersUseCase(landownerRepository)

    @Provides
    @Singleton
    fun provideDeleteDetectionHistoryUseCase(
        detectionHistoryRepository: DetectionHistoryRepository
    ) = DeleteDetectionHistoryUseCase(detectionHistoryRepository)

    @Provides
    @Singleton
    fun provideDeleteLandHistoryUseCase(
        landRepository: LandRepository
    ) = DeleteLandHistoryUseCase(landRepository)

    @Provides
    @Singleton
    fun provideGetFarmersUseCase(
        landownerRepository: LandownerRepository
    ) = GetFarmersUseCase(landownerRepository)

    @Provides
    @Singleton
    fun provideLogoutUseCase(
        authRepository: AuthRepository
    ) = LogoutUseCase(authRepository)

    @Provides
    @Singleton
    fun provideDeleteAccountUseCase(
        authRepository: AuthRepository
    ) = DeleteAccountUseCase(authRepository)

    @Provides
    @Singleton
    fun provideCheckPasswordUseCase(
        authRepository: AuthRepository
    ) = CheckPasswordUseCase(authRepository)

    @Provides
    @Singleton
    fun provideGetLandHistoryByActionTypeUseCase(
        landRepository: LandRepository
    ) = GetLandHistoryByActionTypeUseCase(landRepository)

    @Provides
    @Singleton
    fun provideGetDetectionHistoryByUsernameUseCase(
        detectionHistoryRepository: DetectionHistoryRepository
    ) = GetDetectionHistoryByUsernameUseCase(detectionHistoryRepository)
}