package com.example.hapi.data.repository

import com.example.hapi.data.local.AuthPreference
import com.example.hapi.data.remote.api.FarmerApiService
import javax.inject.Inject

class FarmerRepository @Inject constructor(
    private val farmerApiService: FarmerApiService,
    private val authPreference: AuthPreference
) {

}