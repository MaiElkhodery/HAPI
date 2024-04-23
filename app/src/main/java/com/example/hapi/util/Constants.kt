package com.example.hapi.util

import androidx.datastore.preferences.core.stringSetPreferencesKey

const val AUTH_PREFERENCES = "AUTH_PREF"
val AUTH_KEY = stringSetPreferencesKey("auth_key")
val CropSelection_KEY = stringSetPreferencesKey("crop_selection_key")
val ROLE_KEY = stringSetPreferencesKey("role_key")
val USERNAME_KEY = stringSetPreferencesKey("username_key")
val LAND_ID_KEY = stringSetPreferencesKey("land_id_key")
val CROP_KEY = stringSetPreferencesKey("crop_key")
val LAST_DETECTION_HISTORY_ID_KEY = stringSetPreferencesKey("last_detection_history_id_key")
val LAST_LAND_DATA_HISTORY_ID_KEY = stringSetPreferencesKey("last_land_history_id_key")

const val LANDOWNER = "landowner"
const val FARMER = "farmer"