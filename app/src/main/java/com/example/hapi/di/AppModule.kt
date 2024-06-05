package com.example.hapi.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.hapi.MainActivity
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.util.AUTH_PREFERENCES
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePreferenceDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(AUTH_PREFERENCES)
            }
        )
    }

    @Provides
    @Singleton
    fun provideUserDataPreference(dataStore: DataStore<Preferences>) = UserDataPreference(dataStore)

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

}