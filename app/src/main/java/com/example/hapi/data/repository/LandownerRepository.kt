package com.example.hapi.data.repository

import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.local.room.dao.FarmerDao
import com.example.hapi.data.local.room.entities.Farmer
import com.example.hapi.data.remote.ApiHandler
import com.example.hapi.data.remote.api.LandownerApiService
import com.example.hapi.data.remote.request.SelectCropRequest
import com.example.hapi.data.remote.response.CropRecommendationResponse
import com.example.hapi.data.remote.response.LastFarmerResponse
import com.example.hapi.domain.model.SignupErrorInfo
import com.example.hapi.domain.model.State
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LandownerRepository @Inject constructor(
    private val landownerApiService: LandownerApiService,
    private val userDataPreference: UserDataPreference,
    private val farmerDao: FarmerDao,
    private val apiHandler: ApiHandler
) {

    suspend fun cropRecommendation(): Flow<State<CropRecommendationResponse?>> {
        return apiHandler.makeRequest(
            execute = { landownerApiService.getRecommendedCrops() },
        )
    }

    suspend fun uploadSelectedCrop(crop: String): Flow<State<Unit>> {
        return apiHandler.makeRequest(
            execute = {
                landownerApiService.uploadSelectedCrop(SelectCropRequest(crop = crop))
            },
            onSuccess = {
                userDataPreference.saveCrop(crop)
            }
        )

    }

    suspend fun fetchTanksData(): Flow<State<Boolean>> {
        return flow {
            try {
                emit(State.Loading)
                val response = landownerApiService.getTanksData()
                if (response.isSuccessful) {

                    userDataPreference.saveWaterLevel(response.body()!!.water_level)
                    userDataPreference.saveNitrogenTankLevel("${response.body()!!.npk.N}")
                    userDataPreference.savePhosphorusTankLevel("${response.body()!!.npk.P}")
                    userDataPreference.savePotassiumTankLevel("${response.body()!!.npk.K}")
                    emit(State.Success(true))

                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        SignupErrorInfo::class.java
                    )
                    emit(State.Error(error))
                }
            } catch (e: Exception) {
                emit(State.Exception(e.message.toString()))
            }
        }

    }

    suspend fun getLastFarmer(): Flow<State<LastFarmerResponse>> {
        return withContext(Dispatchers.IO) {
            apiHandler.makeRequest(
                execute = {
                    landownerApiService.getLastFarmer()
                }
            )
        }
    }

    suspend fun fetchFarmers(): Flow<State<List<String>>> {
        return withContext(Dispatchers.IO) {
            apiHandler.makeRequest(
                execute = {
                    landownerApiService.getFarmers()
                },
                onSuccess = {
                    cacheFarmers(it)
                }
            )
        }
    }

    private suspend fun cacheFarmers(farmers: List<String>) {
        withContext(Dispatchers.IO) {
            farmers.forEach {
                farmerDao.addFarmer(Farmer(name = it))
            }
        }
    }

    suspend fun getFarmers(): List<Farmer> {
        return withContext(Dispatchers.IO) {
            farmerDao.getFarmers()
        }
    }

    suspend fun deleteFarmers() {
        withContext(Dispatchers.IO) {
            farmerDao.deleteFarmers()
        }
    }
}