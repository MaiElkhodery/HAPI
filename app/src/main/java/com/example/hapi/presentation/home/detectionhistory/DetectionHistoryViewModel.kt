package com.example.hapi.presentation.home.detectionhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.room.entities.detection_history.DetectionOfHistory
import com.example.hapi.domain.usecase.FetchDetectionHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetectionHistoryViewModel @Inject constructor(
    private val detectionHistoryUseCase: FetchDetectionHistoryUseCase
) : ViewModel() {

    private val _detectionList = MutableStateFlow(emptyList<DetectionOfHistory>())
    val detectionList = _detectionList.asStateFlow()

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    fun getDetectionHistory() {
        viewModelScope.launch {
            val detectionList = detectionHistoryUseCase()
            _detectionList.value = detectionList
        }
    }

}