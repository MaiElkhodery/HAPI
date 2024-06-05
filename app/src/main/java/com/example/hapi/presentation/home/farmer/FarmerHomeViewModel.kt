package com.example.hapi.presentation.home.farmer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.detection.GetLastDetectionUseCase
import com.example.hapi.domain.usecase.detection.FetchDetectionHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FarmerHomeViewModel @Inject constructor(
    private val userDataPreference: UserDataPreference,
    private val fetchDetectionHistoryUseCase: FetchDetectionHistoryUseCase,
    private val getLastDetectionUseCase: GetLastDetectionUseCase
) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _detectionDate = MutableStateFlow("")
    val detectionDate = _detectionDate.asStateFlow()

    private val _detectionTime = MutableStateFlow("")
    val detectionTime = _detectionTime.asStateFlow()

    private val _detectionUsername = MutableStateFlow("")
    val detectionUsername = _detectionUsername.asStateFlow()

    private val _detectionImageUrl = MutableStateFlow("")
    val detectionImageUrl = _detectionImageUrl.asStateFlow()

    private val _detectionRemoteId = MutableStateFlow("")
    val detectionRemoteId = _detectionRemoteId.asStateFlow()

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()


    fun getAndSaveRemoteDetectionHistory() {
        viewModelScope.launch {
            fetchDetectionHistoryUseCase(
                userDataPreference.getLastDetectionHistoryId().toInt()
            ).collect { state ->
                when (state) {
                    is State.Error -> {
                        _loading.value = false
                        Log.d("FARMER HOME", "ERROR")
                    }

                    is State.Exception -> {
                        _loading.value = false
                        Log.d("FARMER HOME", "EXCEPTION")
                    }

                    State.Loading -> {
                        _loading.value = true
                        Log.d("FARMER HOME", "LOADING")
                    }

                    is State.Success -> {
                        _loading.value = false
                        getLastDetectionUseCase()?.let { detection ->
                            userDataPreference.saveLastDetectionHistoryId(detection.remoteId.toString())
                            _detectionImageUrl.value = detection.imageUrl
                            _detectionUsername.value = detection.username
                            _detectionDate.value = detection.date
                            _detectionTime.value = detection.time
                            _detectionRemoteId.value = detection.remoteId.toString()
                        }
                    }

                }
            }
        }
    }

    suspend fun getSavedLastDetection() {
        viewModelScope.launch {
            getLastDetectionUseCase()?.let { detection ->
                userDataPreference.saveLastDetectionHistoryId(detection.remoteId.toString())
                _detectionImageUrl.value = detection.imageUrl
                _detectionUsername.value = detection.username
                _detectionDate.value = detection.date
                _detectionTime.value = detection.time
                _detectionRemoteId.value = detection.remoteId.toString()
            }
        }
    }

    suspend fun getUsername() {
        viewModelScope.launch {
            _username.value = userDataPreference.getUsername()
        }
    }
}