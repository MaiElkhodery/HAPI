package com.example.hapi.presentation.home.detectiondetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.detection.GetCurrentDetectionUseCase
import com.example.hapi.domain.usecase.detection.GetDetectionDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetectionDetailsViewModel @Inject constructor(
    private val getDetectionDetailsUseCase: GetDetectionDetailsUseCase,
    private val getCurrentDetectionUseCase: GetCurrentDetectionUseCase,
    private val userDataPreference: UserDataPreference
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

    private val _imageLocalUri = MutableStateFlow("")
    val imageLocalUri = _imageLocalUri.asStateFlow()

    private val _crop = MutableStateFlow("")
    val crop = _crop.asStateFlow()

    private val _certainty = MutableStateFlow("")
    val certainty = _certainty.asStateFlow()

    private val _diseaseName = MutableStateFlow("")
    val diseaseName = _diseaseName.asStateFlow()

    private val _link = MutableStateFlow("")
    val link = _link.asStateFlow()

    private val _imageUrl = MutableStateFlow("")
    val imageUrl = _imageUrl.asStateFlow()

    private val _role = MutableStateFlow("")
    val role = _role.asStateFlow()

    init {
        viewModelScope.launch {
            _role.value = userDataPreference.getRole()
        }
    }

    fun getRemoteDetectionDetailsById(
        remoteId: Int
    ) {
        viewModelScope.launch {
            getDetectionDetailsUseCase(remoteId).collect { state ->
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
                        _crop.value = state.result!!.crop.uppercase()
                        _certainty.value = state.result.certainty.toString()
                        _time.value = state.result.time
                        _date.value = state.result.date
                        _username.value = state.result.username
                        _imageUrl.value = state.result.image_url
                        _diseaseName.value = state.result.disease_name
                        _link.value = state.result.info_link
                    }

                    is State.Exception -> {
                        _loading.value = false
                        _errorMsg.value = state.msg
                    }
                }
            }
        }
    }

    fun getCachedDiseaseDetectionResult(
        id: Int
    ) {
        viewModelScope.launch {
            val result = getCurrentDetectionUseCase(id)
            _crop.value = result.crop.uppercase()
            _certainty.value = result.certainty.toString()
            _diseaseName.value = result.diseaseName
            _date.value = result.date
            _time.value = result.time
            _username.value = result.username
            _imageLocalUri.value = result.imageLocalUri
            _link.value = result.link
        }
    }
}