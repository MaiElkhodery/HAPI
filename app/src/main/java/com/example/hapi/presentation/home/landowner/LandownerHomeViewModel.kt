package com.example.hapi.presentation.home.landowner

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.data.local.room.entities.detection_history.DetectionOfHistory
import com.example.hapi.domain.usecase.FetchNewestDetectionUseCase
import com.example.hapi.domain.usecase.GetAndSaveDetectionHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandownerHomeViewModel @Inject constructor(
    private val getDetectionHistoryListUseCase: GetAndSaveDetectionHistoryUseCase,
    private val getNewestDetectionUseCase: FetchNewestDetectionUseCase,
    private val userDataPreference: UserDataPreference
) : ViewModel() {

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    private val _lastDetection = MutableStateFlow<DetectionOfHistory?>(null)
    val lastDetection = _lastDetection.asStateFlow()

    private val _id = MutableStateFlow(0)
    val id = _id.asStateFlow()


    fun getDetectionHistory(
        id: Int
    ) {
        viewModelScope.launch {
            getDetectionHistoryListUseCase(id).collect { state ->
                Log.d("LAST DETECTION", "viewmodel, $state")
                when (state) {
                    true -> {
                        Log.d("LAST DETECTION", "viewmodel, true")
                    }

                    false -> {
                        Log.d("LAST DETECTION", "viewmodel, false")
                    }
                }
            }
        }
    }

    suspend fun getLastDetection() {
        viewModelScope.launch {
            _lastDetection.value = getNewestDetectionUseCase()
            setId(_lastDetection.value!!.remoteId.toString())
        }
    }

    private fun getId() {
        viewModelScope.launch {
            _id.value = userDataPreference.getLastHistoryId()!!.toInt()
        }
    }

    private fun setId(lastId: String) {
        viewModelScope.launch {
            userDataPreference.saveLastHistoryId(lastId)
        }
    }

    init {
        getId()
    }
}