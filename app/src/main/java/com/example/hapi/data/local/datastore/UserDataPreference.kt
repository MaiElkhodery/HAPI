package com.example.hapi.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.hapi.util.AUTH_KEY
import com.example.hapi.util.CROP_KEY
import com.example.hapi.util.LAND_ID_KEY
import com.example.hapi.util.LAST_DETECTION_HISTORY_ID_KEY
import com.example.hapi.util.LAST_LAND_DATA_HISTORY_ID_KEY
import com.example.hapi.util.NPK_KEY
import com.example.hapi.util.ROLE_KEY
import com.example.hapi.util.USERNAME_KEY
import com.example.hapi.util.WATER_LEVEL_KEY
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

    suspend fun saveLastDetectionHistoryId(lastHistoryId: String) {
        dataStore.edit { pref ->
            pref[LAST_DETECTION_HISTORY_ID_KEY] = setOf(lastHistoryId)
        }
    }

    suspend fun saveLastLandDataHistoryId(lastHistoryId: String) {
        dataStore.edit { pref ->
            pref[LAST_LAND_DATA_HISTORY_ID_KEY] = setOf(lastHistoryId)
        }
    }

    suspend fun saveWaterLevel(waterLevel: Int) {
        dataStore.edit { pref ->
            pref[WATER_LEVEL_KEY] = setOf(waterLevel.toString())
        }
    }

    suspend fun saveNPK(npk: String) {
        dataStore.edit { pref ->
            pref[NPK_KEY] = setOf(npk)
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

    suspend fun getUsername(): String {
        val preferences = dataStore.data.first()
        return preferences[USERNAME_KEY]?.first() ?: ""
    }

    suspend fun getCrop(): String {
        val preferences = dataStore.data.first()
        return preferences[CROP_KEY]?.first() ?: ""
    }

    suspend fun getLandId(): String? {
        val preferences = dataStore.data.first()
        return preferences[LAND_ID_KEY]?.first()
    }

    suspend fun getLastDetectionHistoryId(): String {
        val preferences = dataStore.data.first()
        return preferences[LAST_DETECTION_HISTORY_ID_KEY]?.first() ?: "1"
    }

    suspend fun getLastLandDataHistoryId(): String {
        val preferences = dataStore.data.first()
        return preferences[LAST_LAND_DATA_HISTORY_ID_KEY]?.first() ?: "1"
    }


    suspend fun getWaterLevel(): String? {
        val preferences = dataStore.data.first()
        return preferences[WATER_LEVEL_KEY]?.first()
    }

    suspend fun getNPK(): String {
        val preferences = dataStore.data.first()
        return preferences[NPK_KEY]?.first() ?: "0 - 0 - 0"
    }
}