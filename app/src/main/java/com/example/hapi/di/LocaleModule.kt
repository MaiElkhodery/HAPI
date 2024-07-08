package com.example.hapi.di

import android.content.Context
import com.example.hapi.util.LocaleHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocaleModule {

    @Provides
    @Singleton
    fun provideLocaleHelper(
        @ApplicationContext context: Context
    ) = LocaleHelper(context)
}