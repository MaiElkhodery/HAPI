package com.example.hapi.presentation.home.landowner

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.domain.model.State
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

    private val _imageUrl = MutableStateFlow("")
    val imageUrl = _imageUrl.asStateFlow()

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _date = MutableStateFlow("")
    val date = _date.asStateFlow()

    private val _time = MutableStateFlow("")
    val time = _time.asStateFlow()

    private val _remoteId = MutableStateFlow(0)
    val remoteId = _remoteId.asStateFlow()

    private val _localId = MutableStateFlow(0)
    val localId = _localId.asStateFlow()


    fun getDetectionHistory() {
        viewModelScope.launch {
            getDetectionHistoryListUseCase(
                userDataPreference.getLastHistoryId().toInt()
            ).collect { state ->
                when (state) {
                    is State.Error -> {
                        Log.d("LANDOWNER HOME", "ERROR")
                    }

                    is State.Exception -> {
                        Log.d("LANDOWNER HOME", "EXCEPTION")
                    }

                    State.Loading -> {
                        Log.d("LANDOWNER HOME", "LOADING")
                    }

                    is State.Success -> {
                        val detection = getNewestDetectionUseCase()
                        userDataPreference.saveLastHistoryId(detection.remoteId.toString())
                        _imageUrl.value = detection.imageUrl
                        _username.value = detection.username
                        _date.value = detection.date
                        _time.value = detection.time
                        _remoteId.value = detection.remoteId
                        _localId.value = detection.id
                    }

                }
            }
        }
    }

    suspend fun getLastDetectionAndSetLastId() {
        viewModelScope.launch {
            val detection = getNewestDetectionUseCase()
            _imageUrl.value = detection.imageUrl
            _username.value = detection.username
            _date.value = detection.date
            _time.value = detection.time
            _remoteId.value = detection.remoteId
            _localId.value = detection.id
        }
    }
}