package com.example.hapi.di

import com.example.hapi.data.local.AuthPreference
import com.example.hapi.data.remote.api.FarmerApiService
import com.example.hapi.data.remote.api.LandownerApiService
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
}