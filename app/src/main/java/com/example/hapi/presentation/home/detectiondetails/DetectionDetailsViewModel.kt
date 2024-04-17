package com.example.hapi.presentation.home.detectiondetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.room.entities.landowner.LandownerDetectionWithDiseases
import com.example.hapi.data.remote.response.DetectionItemResponse
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.DetectionItemUseCase
import com.example.hapi.domain.usecase.GetDetectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetectionDetailsViewModel @Inject constructor(
    private val getDetectionUseCase: GetDetectionUseCase,
    private val getDetectionItemUseCase: DetectionItemUseCase
) : ViewModel() {

    private val _localDetection = MutableStateFlow<LandownerDetectionWithDiseases?>(null)
    val localDetection = _localDetection.asStateFlow()

    private val _remoteDetectionItem = MutableStateFlow<DetectionItemResponse?>(null)
    val remoteDetectionItem = _remoteDetectionItem.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()
    fun getDetection(id: Int) {
        viewModelScope.launch {
            Log.d("DetectionDetailsViewModel", "make request with $id")
            getDetectionItemUseCase(id).collect { state ->
                when (state) {
                    is State.Error -> {
                        _loading.value = false
                        _errorMsg.value = state.error.message
                        Log.d("DetectionDetailsViewModel", "getDetection: ${state.error.message}")

                    }

                    is State.Loading -> {
                        _loading.value = true
                    }

                    is State.Success -> {
                        _loading.value = false
                        _remoteDetectionItem.value = state.result
                    }

                    else -> {

                    }
                }
            }
        }
    }

    fun getCachedDetection(id: Int) {
        viewModelScope.launch {
            _localDetection.value = getDetectionUseCase(id)
            Log.d("DetectionDetailsViewModel", "getDetection: ${localDetection.value!!.detection}")
            Log.d("DetectionDetailsViewModel", "getDetection: ${localDetection.value!!.detection.id}")
        }
    }
}