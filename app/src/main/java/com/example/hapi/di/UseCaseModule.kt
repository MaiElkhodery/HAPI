package com.example.hapi.di

import com.example.hapi.data.repository.LandownerRepository
import com.example.hapi.domain.usecase.LandownerSignupUseCase
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
        landownerRepository: LandownerRepository
    ) = LandownerSignupUseCase(landownerRepository)
}