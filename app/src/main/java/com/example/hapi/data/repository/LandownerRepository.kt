package com.example.hapi.data.repository

import com.example.hapi.data.local.AuthPreference
import com.example.hapi.data.remote.api.LandownerApiService
import javax.inject.Inject

class LandownerRepository @Inject constructor(
    private val landownerApiService: LandownerApiService,
    private val authPreference: AuthPreference
) {

}