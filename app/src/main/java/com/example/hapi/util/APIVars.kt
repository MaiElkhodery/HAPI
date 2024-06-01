package com.example.hapi.util


const val BASE_URL = "https://a7ac-154-239-106-12.ngrok-free.app"
const val LANDOWNER_SIGNUP = "$BASE_URL/api/signup/landowner"
const val FARMER_SIGNUP = "$BASE_URL/api/signup/farmer"
const val SIGNIN = "$BASE_URL/api/login"
const val RECOMMENDATIONS = "$BASE_URL/api/crop/recommendation"
const val SELECTED_CROP = "$BASE_URL/api/crop/selecting-manual"
const val DETECTION = "$BASE_URL/api/detect"
const val DETECTION_HISTORY = "$BASE_URL/api/detections"
const val DETECTION_ITEM = "$BASE_URL/api/detections/{id}"
const val LAND_HISTORY = "$BASE_URL/api/land/history"
const val LAND_DATA = "$BASE_URL/api/land/data"
const val LAST_FARMER = "$BASE_URL/api/land/latestFarmer"
const val FARMERS = "$BASE_URL/api/settings/farmers"
const val LOGOUT = "$BASE_URL/api/logout"
const val DELETE_ACCOUNT = "$BASE_URL/api/settings/account"
const val PASSWORD_CHECK = "$BASE_URL/api/settings/check-password"