package com.example.hapi.presentation.home.detectionhistory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.room.entities.history.DetectionWithDiseases
import com.example.hapi.data.remote.response.DetectionHistoryResponse
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.GetDetectionHistoryListUseCase
import com.example.hapi.domain.usecase.FetchLastFiveDetectionsUseCase
import com.example.hapi.domain.usecase.GetSavedLastFiveDetectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetectionHistoryViewModel @Inject constructor(
    private val detectionHistoryUseCase: GetDetectionHistoryListUseCase,
    private val lastFiveDetectionsUseCase: FetchLastFiveDetectionsUseCase,
    private val localDetectionsUseCase: GetSavedLastFiveDetectionsUseCase
) : ViewModel() {

    private val _detectionList = MutableStateFlow(emptyList<DetectionHistoryResponse>())
    val detectionList = _detectionList.asStateFlow()

    private val _localDetections = MutableStateFlow(emptyList<DetectionWithDiseases>())
    val localDetections = _localDetections.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    fun getDetectionHistory() {
        viewModelScope.launch {
            detectionHistoryUseCase().collect { state ->
                when (state) {
                    is State.Error -> {
                        _loading.value = false
                        _errorMsg.value = state.error.message
                    }

                    is State.Loading -> {
                        _loading.value = true
                    }

                    is State.Success -> {
                        _loading.value = false
                        _detectionList.value = state.result!!
                        Log.d("DetectionHistoryViewModel", "getDetectionHistory: ${state.result}")
                    }

                    else -> {

                    }
                }
            }

        }
    }
    fun getAndSaveLastFiveDetections() {
        viewModelScope.launch {
            val result = lastFiveDetectionsUseCase()
            Log.d("DetectionHistoryViewModel", "getAndSaveLastFiveDetections: $result")
        }
    }

    fun getLocalDetections() {
        viewModelScope.launch {
            _localDetections.value = localDetectionsUseCase()
            Log.d("DetectionHistoryViewModel", "getLocalDetections: ${_localDetections.value}")
        }
    }
}