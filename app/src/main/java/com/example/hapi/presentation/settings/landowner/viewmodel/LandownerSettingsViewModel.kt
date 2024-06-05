package com.example.hapi.presentation.settings.landowner.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.detection.DeleteDetectionHistoryUseCase
import com.example.hapi.domain.usecase.land.DeleteLandHistoryUseCase
import com.example.hapi.domain.usecase.landowner.DeleteFarmersUseCase
import com.example.hapi.domain.usecase.sign.DeleteAccountUseCase
import com.example.hapi.domain.usecase.sign.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandownerSettingsViewModel @Inject constructor(
    private val userDataPreference: UserDataPreference,
    private val deleteFarmersUseCase: DeleteFarmersUseCase,
    private val deleteLandHistoryUseCase: DeleteLandHistoryUseCase,
    private val deleteDetectionHistoryUseCase: DeleteDetectionHistoryUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase
) : ViewModel() {

    private val _landId = MutableStateFlow("")
    val landId = _landId.asStateFlow()

    private val _isLoggedOut = MutableStateFlow(false)
    val isLoggedOut = _isLoggedOut.asStateFlow()


    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _logout = MutableStateFlow(false)
    val logout = _logout.asStateFlow()

    private val _deleteAccount = MutableStateFlow(false)
    val deleteAccount = _deleteAccount.asStateFlow()

    fun getLandId() {
        viewModelScope.launch {
            _landId.value = userDataPreference.getLandId()!!
        }
    }

    suspend fun deleteAccount() {
        viewModelScope.launch {
            deleteAccountUseCase(_password.value).collect { state ->
                when (state) {
                    is State.Error -> {
                        Log.d("LandownerSettingsViewModel", "error")
                    }

                    is State.Exception -> {
                        Log.d("LandownerSettingsViewModel", "exception, ${state.msg}")
                    }

                    State.Loading -> {
                        Log.d("LandownerSettingsViewModel", "loading")
                    }

                    is State.Success -> {
                        Log.d("LandownerSettingsViewModel", "success")
                        clearDB()
                        _isLoggedOut.value = true
                    }
                }
            }
        }
    }

    suspend fun logout() {
        viewModelScope.launch {
            logoutUseCase().collect { state ->
                when (state) {
                    is State.Error -> {
                        Log.d("LandownerSettingsViewModel", "error")
                    }

                    is State.Exception -> {
                        Log.d("LandownerSettingsViewModel", "exception")
                    }

                    State.Loading -> {
                        Log.d("LandownerSettingsViewModel", "loading")
                    }

                    is State.Success -> {
                        Log.d("LandownerSettingsViewModel", "success")
                        clearDB()
                        _isLoggedOut.value = true
                    }
                }
            }
        }
    }


    private fun clearDB() {
        viewModelScope.launch {
            deleteFarmersUseCase()
            deleteDetectionHistoryUseCase()
            deleteLandHistoryUseCase()
        }
    }

    fun onEvent(event: LandownerSettingsEvent) {
        when (event) {
            is LandownerSettingsEvent.ChangePassword -> {
                _password.value = event.password
            }

            LandownerSettingsEvent.OnClickDeleteAccount -> {
                _deleteAccount.value = true
            }

            LandownerSettingsEvent.OnClickLogout -> {
                _logout.value = true
            }
        }
    }
}