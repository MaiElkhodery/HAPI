package com.example.hapi.presentation.detection.imageselection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.detection.DetectDiseaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageSelectionViewModel @Inject constructor(
    private val detectDiseaseUseCase: DetectDiseaseUseCase
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    private var _detectionId = MutableStateFlow(null as Long?)
    val detectionId = _detectionId.asStateFlow()

    fun detectDisease(
        selectedCrop: String,
        byteArrayImage: ByteArray,
        imageLocalUrl: String
    ) {
        viewModelScope.launch {
            detectDiseaseUseCase(selectedCrop, byteArrayImage,imageLocalUrl).collect { state ->
                when (state) {
                    is State.Loading -> {
                        _loading.value = true
                        Log.d("DetectionViewModel", "loading")
                    }

                    is State.Success -> {
                        Log.d("DetectionViewModel", "detectDisease: ${state.result}")
                        _loading.value = false
                        _detectionId.value = state.result!!

                    }

                    is State.Error -> {
                        _loading.value = false
                        _errorMsg.value = state.error.message
                        Log.d("DetectionViewModel", "detectDisease: ${state.error.message}")
                    }

                    else -> {}
                }
            }
        }
    }

    fun resetError(){
        _errorMsg.value = ""
    }
}