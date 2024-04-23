package com.example.hapi.presentation.home.landowner

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.FetchNewestDetectionUseCase
import com.example.hapi.domain.usecase.GetAndSaveDetectionHistoryUseCase
import com.example.hapi.domain.usecase.GetAndSaveLandDataUseCase
import com.example.hapi.domain.usecase.GetLastLandDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandownerHomeViewModel @Inject constructor(
    private val getDetectionHistoryListUseCase: GetAndSaveDetectionHistoryUseCase,
    private val getNewestDetectionUseCase: FetchNewestDetectionUseCase,
    private val userDataPreference: UserDataPreference,
    private val getLandDataHistoryUseCase: GetAndSaveLandDataUseCase,
    private val getLastLandDataUseCase: GetLastLandDataUseCase
) : ViewModel() {

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _imageUrl = MutableStateFlow("")
    val imageUrl = _imageUrl.asStateFlow()

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _detectionDate = MutableStateFlow("")
    val detectionDate = _detectionDate.asStateFlow()

    private val _detectionTime = MutableStateFlow("")
    val detectionTime = _detectionTime.asStateFlow()

    private val _detectionRemoteId = MutableStateFlow(0)
    val detectionRemoteId = _detectionRemoteId.asStateFlow()

    private val _landActionType = MutableStateFlow("")
    val landActionType = _landActionType.asStateFlow()

    private val _landActionDate = MutableStateFlow("")
    val landActionDate = _landActionDate.asStateFlow()

    private val _landActionTime = MutableStateFlow("")
    val landActionTime = _landActionTime.asStateFlow()


    fun getDetectionHistory() {
        viewModelScope.launch {
            getDetectionHistoryListUseCase(
                userDataPreference.getLastDetectionHistoryId().toInt()
            ).collect { state ->
                when (state) {
                    is State.Error -> {
                        _loading.value = false
                        Log.d("LANDOWNER HOME", "ERROR")
                    }

                    is State.Exception -> {
                        _loading.value = false
                        Log.d("LANDOWNER HOME", "EXCEPTION")
                    }

                    State.Loading -> {
                        _loading.value = true
                        Log.d("LANDOWNER HOME", "LOADING")
                    }

                    is State.Success -> {
                        _loading.value = false
                        getNewestDetectionUseCase()?.let { detection ->
                            userDataPreference.saveLastDetectionHistoryId(detection.remoteId.toString())
                            _imageUrl.value = detection.imageUrl
                            _username.value = detection.username
                            _detectionDate.value = detection.date
                            _detectionTime.value = detection.time
                            _detectionRemoteId.value = detection.remoteId
                        }
                    }

                }
            }
        }
    }

    suspend fun getLandDataHistory() {
        viewModelScope.launch {
            getLandDataHistoryUseCase(
                userDataPreference.getLastLandDataHistoryId().toInt()
            ).collect { state ->
                Log.d("LAST LAND ID", userDataPreference.getLastLandDataHistoryId())
                when (state) {
                    is State.Error -> {
                        _loading.value = false
                        Log.d("LANDOWNER HOME", "LAND:ERROR")
                    }

                    is State.Exception -> {
                        _loading.value = false
                        Log.d("LANDOWNER HOME", "LAND:EXCEPTION")
                    }

                    State.Loading -> {
                        _loading.value = false
                        Log.d("LANDOWNER HOME", "LAND:LOADING")
                    }

                    is State.Success -> {
                        Log.d("LANDOWNER HOME", "LAND:LOADING")
                        getLastLandDataUseCase()?.let { landData ->
                            Log.d("LAST LAND DATA RESULT",landData.toString())
                            userDataPreference.saveLastLandDataHistoryId(landData.remote_id.toString())
                            _landActionType.value = landData.action_type
                            _landActionDate.value = landData.date
                            _landActionTime.value = landData.time
                        }

                    }

                }
            }
        }
    }

    suspend fun getLastLandData() {
        viewModelScope.launch {
            getLastLandDataUseCase()?.let { landData ->
                userDataPreference.saveLastLandDataHistoryId(landData.remote_id.toString())
                _landActionType.value = landData.action_type
                _landActionDate.value = landData.date
                _landActionTime.value = landData.time
            }
        }
    }

    suspend fun getLastDetectionAndSetLastId() {
        viewModelScope.launch {
            getNewestDetectionUseCase()?.let { detection ->
                userDataPreference.saveLastDetectionHistoryId(detection.remoteId.toString())
                _imageUrl.value = detection.imageUrl
                _username.value = detection.username
                _detectionDate.value = detection.date
                _detectionTime.value = detection.time
                _detectionRemoteId.value = detection.remoteId
            }
        }
    }


    fun getUsername() {
        viewModelScope.launch {
            _username.value = userDataPreference.getUsername()!!
        }
    }
}