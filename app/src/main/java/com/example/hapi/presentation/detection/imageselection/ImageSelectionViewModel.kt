package com.example.hapi.presentation.detection.imageselection

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

    private val _error = MutableStateFlow(false)
    val error = _error.asStateFlow()

    private var _detectionId = MutableStateFlow(null as Long?)
    val detectionId = _detectionId.asStateFlow()

    fun detectDisease(
        selectedCrop: String,
        byteArrayImage: ByteArray,
        imageLocalUrl: String
    ) {
        viewModelScope.launch {
            detectDiseaseUseCase(selectedCrop, byteArrayImage, imageLocalUrl).collect { state ->
                when (state) {
                    is State.Loading -> {
                        _loading.value = true
                    }

                    is State.Success -> {
                        _loading.value = false
                        _detectionId.value = state.result!!

                    }

                    else->{
                        _loading.value = false
                        _error.value = true
                    }
                }
            }
        }
    }

    fun resetId() {
        _detectionId.value = null
    }

    fun resetError() {
        _error.value = false
    }
}