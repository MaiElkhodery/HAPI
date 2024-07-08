package com.example.hapi.di

import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.remote.api.AuthApiService
import com.example.hapi.data.remote.api.DetectionApiService
import com.example.hapi.data.remote.api.LandApiService
import com.example.hapi.data.remote.api.LandownerApiService
import com.example.hapi.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance(userDataPreference: UserDataPreference): Retrofit {
        val client = OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val token = runBlocking { userDataPreference.getToken() }
                val newRequest = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .apply {
                        if (token != null) addHeader("Authorization", "Bearer $token")
                    }
                    .build()
                chain.proceed(newRequest)
            }
        }.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY

        }).connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiLandownerServiceInstance(
        retrofit: Retrofit
    ): LandownerApiService {
        return retrofit.create(LandownerApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiFarmerServiceInstance(
        retrofit: Retrofit
    ): LandApiService {
        return retrofit.create(LandApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiAuthServiceInstance(
        retrofit: Retrofit
    ): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiDetectionServiceInstance(
        retrofit: Retrofit
    ): DetectionApiService {
        return retrofit.create(DetectionApiService::class.java)
    }
}

