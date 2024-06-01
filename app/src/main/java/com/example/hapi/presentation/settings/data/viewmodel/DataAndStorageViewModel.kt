package com.example.hapi.presentation.settings.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.detection.DeleteDetectionHistoryUseCase
import com.example.hapi.domain.usecase.detection.GetLastDetectionUseCase
import com.example.hapi.domain.usecase.land.DeleteLandHistoryUseCase
import com.example.hapi.domain.usecase.land.GetLastLandHistoryItemUseCase
import com.example.hapi.domain.usecase.sign.CheckPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataAndStorageViewModel @Inject constructor(
    private val deleteLandHistoryUseCase: DeleteLandHistoryUseCase,
    private val deleteDetectionHistoryUseCase: DeleteDetectionHistoryUseCase,
    private val getLastLandHistoryItemUseCase: GetLastLandHistoryItemUseCase,
    private val fetchLastDetectionUseCase: GetLastDetectionUseCase,
    private val checkPasswordUseCase: CheckPasswordUseCase,
) : ViewModel() {

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _isPasswordCorrect = MutableStateFlow(false)
    val isPasswordCorrect = _isPasswordCorrect.asStateFlow()
    fun deleteLandHistory() {
        viewModelScope.launch {
            deleteLandHistoryUseCase()
            //TODO:THIS FEATURE SHOULD BE TESTED
            getLastLandHistoryItemUseCase()
        }
    }

    fun deleteDetectionHistory() {
        viewModelScope.launch {
            deleteDetectionHistoryUseCase()
            //TODO:THIS FEATURE SHOULD BE TESTED
            fetchLastDetectionUseCase()
        }
    }

    fun onChangePassword(password: String) {
        _password.value = password
    }

    suspend fun checkIfPasswordIsCorrect() {
        viewModelScope.launch {
            checkPasswordUseCase(_password.value).collect { state ->
                when (state) {
                    is State.Error -> {
                        Log.d("DataAndStorageViewModel", "checkIfPasswordIsCorrect: false")
                    }

                    is State.Exception -> {
                        Log.d("DataAndStorageViewModel", "checkIfPasswordIsCorrect: false")
                        _isPasswordCorrect.value = false
                    }

                    State.Loading -> {
                        Log.d("DataAndStorageViewModel", "checkIfPasswordIsCorrect: loading")
                    }

                    is State.Success -> {
                        Log.d("DataAndStorageViewModel", "checkIfPasswordIsCorrect: true}")
                        _isPasswordCorrect.value = true
                    }
                }
            }
        }
    }
}