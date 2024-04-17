package com.example.hapi.presentation.home.landowner

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.room.entities.history.DetectionWithDiseases
import com.example.hapi.data.remote.response.DetectionHistoryResponse
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.GetRemoteLastDetectionUseCase
import com.example.hapi.domain.usecase.GetSavedLastFiveDetectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandownerHomeViewModel @Inject constructor(
    private val getLastDetectionUseCase: GetRemoteLastDetectionUseCase,
    private val localDetectionsUseCase: GetSavedLastFiveDetectionsUseCase
) : ViewModel() {
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    private val _lastDetection = MutableStateFlow(
        DetectionHistoryResponse(0, "", "", "", "")
    )
    val lastDetection = _lastDetection.asStateFlow()

    private val _lastLocalDetection = MutableStateFlow<DetectionWithDiseases?>(null)
    val lastLocalDetection = _lastLocalDetection.asStateFlow()

    fun getLastDetection() {
        viewModelScope.launch {
            getLastDetectionUseCase().collect { state ->
                Log.d("LAST DETECTION", "viewmodel, $state")
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
                        _lastDetection.value = state.result!!
                    }

                    else -> TODO()
                }
            }
        }
    }

    suspend fun getLastLocalDetection() {
        viewModelScope.launch {
            _lastLocalDetection.value = localDetectionsUseCase().first()
        }
    }
}