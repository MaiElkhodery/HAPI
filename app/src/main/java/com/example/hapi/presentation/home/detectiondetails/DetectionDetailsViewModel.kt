package com.example.hapi.presentation.home.detectiondetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.domain.model.Disease
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.GetLocalCurrentDetectionUseCase
import com.example.hapi.domain.usecase.GetLocalDetectionUseCase
import com.example.hapi.domain.usecase.GetRemoteDetectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetectionDetailsViewModel @Inject constructor(
    private val getDetectionDetailsUseCase: GetRemoteDetectionUseCase,
    private val getLocalDetectionUseCase: GetLocalDetectionUseCase,
    private val getLocalCurrentDetectionUseCase: GetLocalCurrentDetectionUseCase

) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _date = MutableStateFlow("")
    val date = _date.asStateFlow()

    private val _time = MutableStateFlow("")
    val time = _time.asStateFlow()

    private val _byteArrayImage = MutableStateFlow(ByteArray(0))
    val byteArrayImage = _byteArrayImage.asStateFlow()

    private val _crop = MutableStateFlow("")
    val crop = _crop.asStateFlow()

    private val _confidence = MutableStateFlow(0.0f)
    val confidence = _confidence.asStateFlow()

    private val _diseaseList = MutableStateFlow(emptyList<Disease>())
    val diseaseList = _diseaseList.asStateFlow()

    fun getRemoteDetectionDetailsById(
        remoteId: Int
    ) {
        viewModelScope.launch {
            Log.d("DetectionDetailsViewModel", "make request with $remoteId")
            getDetectionDetailsUseCase(remoteId).collect { state ->
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
                        _crop.value = state.result!!.crop
                        _confidence.value = state.result.detection.confidence
                        _diseaseList.value = state.result.detection.diseases
                    }

                    else -> {

                    }
                }
            }
        }
    }

    fun getLocalDetection(id: Int) {
        viewModelScope.launch {
            val detection = getLocalDetectionUseCase(id)
            _date.value = detection.date
            _time.value = detection.time
            _username.value = detection.username
            _byteArrayImage.value = detection.imageByteArray
        }
    }

    fun getCachedDiseaseDetectionResult(
        id: Int
    ){
        viewModelScope.launch {
            val result = getLocalCurrentDetectionUseCase(id)
            _crop.value = result.detection.crop
            _confidence.value = result.detection.confidence
            _diseaseList.value = result.diseases.map {
                Disease(
                    name = it.name,
                    confidence = it.confidence,
                    infoLink = it.infoLink
                )
            }
            _date.value = result.detection.date
            _time.value = result.detection.time
            _username.value = result.detection.detectionMaker
            _byteArrayImage.value = result.detection.image
        }
    }
}