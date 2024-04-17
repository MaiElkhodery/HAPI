package com.example.hapi.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.hapi.util.AUTH_KEY
import com.example.hapi.util.CROP_KEY
import com.example.hapi.util.LAND_ID_KEY
import com.example.hapi.util.ROLE_KEY
import com.example.hapi.util.USERNAME_KEY
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserDataPreference @Inject constructor(
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

    suspend fun saveUsername(username: String) {
        dataStore.edit { pref ->
            pref[USERNAME_KEY] = setOf(username)
        }
    }

    suspend fun saveCrop(crop: String) {
        dataStore.edit { pref ->
            pref[CROP_KEY] = setOf(crop)
        }
    }

    suspend fun saveLandId(landId: String) {
        dataStore.edit { pref ->
            pref[LAND_ID_KEY] = setOf(landId)
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

    suspend fun getUsername(): String? {
        val preferences = dataStore.data.first()
        return preferences[USERNAME_KEY]?.first()
    }

    suspend fun getCrop(): String? {
        val preferences = dataStore.data.first()
        return preferences[CROP_KEY]?.first()
    }

    suspend fun getLandId(): String? {
        val preferences = dataStore.data.first()
        return preferences[LAND_ID_KEY]?.first()
    }
}