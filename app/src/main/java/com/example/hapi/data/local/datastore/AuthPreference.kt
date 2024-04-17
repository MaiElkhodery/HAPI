package com.example.hapi.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.hapi.util.AUTH_KEY
import com.example.hapi.util.ROLE_KEY
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthPreference @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun saveAuthToken(token: String) {
        dataStore.edit { pref ->
            pref[AUTH_KEY] = setOf(token)
        }
    }
    suspend fun saveRole(role: String) {
        dataStore.edit { pref ->
            pref[ROLE_KEY] = setOf(role)
        }
    }

    suspend fun getToken(): String? {
        val preferences = dataStore.data.first()
        return preferences[AUTH_KEY]?.first()
    }

    suspend fun getRole(): String? {
        val preferences = dataStore.data.first()
        return preferences[ROLE_KEY]?.first()
    }
}