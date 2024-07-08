package com.example.hapi.presentation.home.landowner.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.detection.FetchDetectionHistoryUseCase
import com.example.hapi.domain.usecase.detection.GetLastDetectionUseCase
import com.example.hapi.domain.usecase.land.FetchLandHistoryUseCase
import com.example.hapi.domain.usecase.land.GetLastLandHistoryItemUseCase
import com.example.hapi.domain.usecase.landowner.FetchTanksDataUseCase
import com.example.hapi.domain.usecase.landowner.GetLastFarmerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandownerHomeViewModel @Inject constructor(
    private val fetchDetectionHistoryUseCase: FetchDetectionHistoryUseCase,
    private val getLastDetectionUseCase: GetLastDetectionUseCase,
    private val userDataPreference: UserDataPreference,
    private val fetchLandHistoryUseCase: FetchLandHistoryUseCase,
    private val getLastLandHistoryItemUseCase: GetLastLandHistoryItemUseCase,
    private val fetchTanksDataUseCase: FetchTanksDataUseCase,
    private val getLastFarmerUseCase: GetLastFarmerUseCase
) : ViewModel() {

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    private val _imageUrl = MutableStateFlow("")
    val imageUrl = _imageUrl.asStateFlow()

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _detectionDate = MutableStateFlow("")
    val detectionDate = _detectionDate.asStateFlow()

    private val _detectionTime = MutableStateFlow("")
    val detectionTime = _detectionTime.asStateFlow()

    private val _detectionUsername = MutableStateFlow("")
    val detectionUsername = _detectionUsername.asStateFlow()

    private val _detectionRemoteId = MutableStateFlow(0)
    val detectionRemoteId = _detectionRemoteId.asStateFlow()

    private val _landActionType = MutableStateFlow("")
    val landActionType = _landActionType.asStateFlow()

    private val _landActionDate = MutableStateFlow("")
    val landActionDate = _landActionDate.asStateFlow()

    private val _landActionTime = MutableStateFlow("")
    val landActionTime = _landActionTime.asStateFlow()

    private val _waterLevel = MutableStateFlow("0")
    val waterLevel = _waterLevel.asStateFlow()

    private var _nitrogen = MutableStateFlow("0")
    val nitrogen = _nitrogen.asStateFlow()

    private var _phosphorus = MutableStateFlow("0")
    val phosphorus = _phosphorus.asStateFlow()

    private var _potassium = MutableStateFlow("0")
    val potassium = _potassium.asStateFlow()

    private var _crop = MutableStateFlow("")
    val crop = _crop.asStateFlow()

    private val _lastFarmerUsername = MutableStateFlow("")
    val lastFarmerUsername = _lastFarmerUsername.asStateFlow()

    private val _lastFarmerDate = MutableStateFlow("")
    val lastFarmerDate = _lastFarmerDate.asStateFlow()

    private val _lastFarmerTime = MutableStateFlow("")
    val lastFarmerTime = _lastFarmerTime.asStateFlow()

    fun fetchDetectionHistory() {
        viewModelScope.launch {
            fetchDetectionHistoryUseCase(
                userDataPreference.getLastDetectionHistoryId().toInt()
            ).collect { state ->
                when (state) {
                    is State.Error -> {
                        _loading.value = false
                    }

                    is State.Exception -> {
                        _loading.value = false
                    }

                    State.Loading -> {
                        _loading.value = true
                    }

                    is State.Success -> {
                        _loading.value = false
                        getLastDetection()
                    }
                }
            }
        }
    }

    suspend fun fetchLandHistory() {
        viewModelScope.launch {
            fetchLandHistoryUseCase(
                userDataPreference.getLastLandDataHistoryId().toInt()
            ).collect {
                when (it) {
                    is State.Error -> {
                        _loading.value = false
                    }

                    is State.Exception -> {
                        _loading.value = false
                    }

                    State.Loading -> {
                        _loading.value = true
                    }

                    is State.Success -> {
                        _loading.value = false
                        getLastLandHistoryItem()
                    }
                }

            }
        }
    }

    fun fetchTanksData() {
        viewModelScope.launch {
            fetchTanksDataUseCase().collect { state ->
                when (state) {
                    is State.Error -> {
                        _loading.value = false
                    }

                    is State.Exception -> {
                        _loading.value = false
                    }

                    State.Loading -> {
                        _loading.value = true
                    }

                    is State.Success -> {
                        _loading.value = false
                        _waterLevel.value = userDataPreference.getWaterLevel()
                        _nitrogen.value = userDataPreference.getNitrogenTankLevel()
                        _phosphorus.value = userDataPreference.getPhosphorusTankLevel()
                        _potassium.value = userDataPreference.getPotassiumTankLevel()
                    }
                }
            }
        }
    }

    fun getLastLandHistoryItem() {
        viewModelScope.launch {
            getLastLandHistoryItemUseCase().let { landData ->
                if (landData != null) {
                    _landActionType.value = landData.action_type
                    _landActionDate.value = landData.date
                    _landActionTime.value = landData.time
                } else {
                    _landActionType.value = ""
                    _landActionDate.value = ""
                    _landActionTime.value = ""
                }
            }
        }
    }

    fun getLastDetection() {
        viewModelScope.launch {
            getLastDetectionUseCase().let { detection ->
                Log.d("Detection", "getLastDetection: $detection")
                if (detection != null) {
                    _imageUrl.value = detection.imageUrl
                    _detectionUsername.value = detection.username
                    _detectionDate.value = detection.date
                    _detectionTime.value = detection.time
                    _detectionRemoteId.value = detection.remoteId
                } else {
                    _imageUrl.value = ""
                    _detectionUsername.value = ""
                    _detectionDate.value = ""
                    _detectionTime.value = ""
                    _detectionRemoteId.value = 0
                }
            }
        }
    }

    private fun getUsername() {
        viewModelScope.launch {
            _username.value = userDataPreference.getUsername()
        }
    }

    fun getTanksData() {
        viewModelScope.launch {
            _waterLevel.value = userDataPreference.getWaterLevel()
            _nitrogen.value = userDataPreference.getNitrogenTankLevel()
            _phosphorus.value = userDataPreference.getPhosphorusTankLevel()
            _potassium.value = userDataPreference.getPotassiumTankLevel()
        }
    }

    private fun getCrop() {
        viewModelScope.launch {
            _crop.value = userDataPreference.getCrop().uppercase()
        }
    }

    fun getRemoteLastFarmer() {
        viewModelScope.launch {
            getLastFarmerUseCase().collect { state ->
                when (state) {
                    is State.Error -> {
                        _loading.value = false
                    }

                    is State.Exception -> {
                        _loading.value = false
                    }

                    State.Loading -> {
                        _loading.value = true
                    }

                    is State.Success -> {
                        _loading.value = false
                        _lastFarmerDate.value = state.result!!.date
                        _lastFarmerTime.value = state.result.time
                        _lastFarmerUsername.value = state.result.username
                    }
                }
            }
        }
    }

    init {
        getCrop()
        getUsername()
        getTanksData()
        getLastDetection()
        getLastLandHistoryItem()

    }
}