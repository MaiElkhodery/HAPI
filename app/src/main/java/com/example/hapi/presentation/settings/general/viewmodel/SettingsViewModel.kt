package com.example.hapi.presentation.settings.general.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.datastore.UserDataPreference
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.detection.DeleteDetectionHistoryUseCase
import com.example.hapi.domain.usecase.land.DeleteLandHistoryUseCase
import com.example.hapi.domain.usecase.landowner.DeleteFarmersUseCase
import com.example.hapi.domain.usecase.sign.CheckPasswordUseCase
import com.example.hapi.domain.usecase.sign.DeleteAccountUseCase
import com.example.hapi.domain.usecase.sign.LogoutUseCase
import com.example.hapi.util.LANDOWNER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userDataPreference: UserDataPreference,
    private val deleteFarmersUseCase: DeleteFarmersUseCase,
    private val deleteLandHistoryUseCase: DeleteLandHistoryUseCase,
    private val deleteDetectionHistoryUseCase: DeleteDetectionHistoryUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val checkPasswordUseCase: CheckPasswordUseCase
) : ViewModel() {

    private val _landId = MutableStateFlow("")
    val landId = _landId.asStateFlow()

    private val _role = MutableStateFlow("")
    val role = _role.asStateFlow()

    private val _isLoggedOut = MutableStateFlow(false)
    val isLoggedOut = _isLoggedOut.asStateFlow()

    private val _passwordError = MutableStateFlow(false)
    val passwordError = _passwordError.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()


    init {
        viewModelScope.launch {
            getLandId()
            getRole()
        }
    }

    private suspend fun getLandId() {
        _landId.value = userDataPreference.getLandId()!!
    }

    private suspend fun getRole() {
        _role.value = userDataPreference.getRole()
    }

    fun checkPassword(action: suspend () -> Unit = {}) {
        viewModelScope.launch {
            checkPasswordUseCase(_password.value).collect { state ->
                when (state) {
                    is State.Success -> {
                        action()
                        _passwordError.value = false
                    }

                    else -> {
                        _passwordError.value = true
                    }
                }
            }
        }
    }

    private fun deleteAccount() {
        viewModelScope.launch {
            checkPassword {
                deleteAccountUseCase().collect { state ->
                    when (state) {
                        is State.Success -> {
                            clearDB()
                            _isLoggedOut.value = true
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            checkPassword {
                logoutUseCase().collect { state ->
                    when (state) {

                        is State.Success -> {
                            clearDB()
                            _isLoggedOut.value = true
                        }

                        else -> {}
                    }
                }
            }
        }
    }


    private fun clearDB() {
        viewModelScope.launch {
            if (_role.value == LANDOWNER) {
                deleteFarmersUseCase()
                deleteLandHistoryUseCase()
            }
            deleteDetectionHistoryUseCase()
        }
    }

    fun deleteLandHistory() {
        viewModelScope.launch {
            deleteLandHistoryUseCase()
        }
    }

    fun deleteDetectionHistory() {
        viewModelScope.launch {
            deleteDetectionHistoryUseCase()
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ChangePassword -> {
                _password.value = event.password
            }

            SettingsEvent.OnClickDeleteAccount -> {
                deleteAccount()
            }

            SettingsEvent.OnClickLogout -> {
                logout()
            }

            SettingsEvent.ResetPasswordError->{
                _passwordError.value = false
            }
        }
    }
}