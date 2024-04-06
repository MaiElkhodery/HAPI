package com.example.hapi.util

import androidx.datastore.preferences.core.stringSetPreferencesKey

const val AUTH_PREFERENCES = "AUTH_PREF"
val AUTH_KEY = stringSetPreferencesKey("auth_key")
val CropSelection_KEY = stringSetPreferencesKey("crop_selection_key")
val ROLE_KEY = stringSetPreferencesKey("role_key")

const val LANDOWNER = "landowner"
const val FARMER = "farmer"