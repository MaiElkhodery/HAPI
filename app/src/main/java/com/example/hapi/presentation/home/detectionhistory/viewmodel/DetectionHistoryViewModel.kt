package com.example.hapi.presentation.home.detectionhistory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.room.entities.DetectionOfHistory
import com.example.hapi.domain.usecase.detection.GetDetectionHistoryByUsernameUseCase
import com.example.hapi.domain.usecase.detection.GetDetectionHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetectionHistoryViewModel @Inject constructor(
    private val getDetectionHistoryUseCase: GetDetectionHistoryUseCase,
    private val detectionHistoryByUsernameUseCase: GetDetectionHistoryByUsernameUseCase,
) : ViewModel() {

    private val _detectionList = MutableStateFlow(emptyList<DetectionOfHistory>())
    val detectionList = _detectionList.asStateFlow()

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    private val _isAllDetectionsSelected = MutableStateFlow(true)
    val isAllDetectionsSelected = _isAllDetectionsSelected.asStateFlow()

    fun getDetectionHistory() {
        viewModelScope.launch {
            getDetectionHistoryUseCase()?.let {
                _detectionList.value = it
            }
        }
    }

    fun getDetectionHistoryByUsername() {
        viewModelScope.launch {
            detectionHistoryByUsernameUseCase()?.let {
                _detectionList.value = it
            }
        }
    }

    fun modifyIsAllDetectionsSelected(isAllDetectionsSelected: Boolean) {
        _isAllDetectionsSelected.value = isAllDetectionsSelected
    }
}