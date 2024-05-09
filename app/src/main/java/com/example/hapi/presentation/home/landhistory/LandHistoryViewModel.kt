package com.example.hapi.presentation.home.landhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.room.entities.land_history.LandData
import com.example.hapi.domain.usecase.land.GetLandHistoryByActionTypeUseCase
import com.example.hapi.domain.usecase.land.GetLandHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandHistoryViewModel @Inject constructor(
    private val getLandHistoryUseCase: GetLandHistoryUseCase,
    private val getLandHistoryByActionTypeUseCase: GetLandHistoryByActionTypeUseCase
) : ViewModel() {

    private val _landHistory = MutableStateFlow(emptyList<LandData>())
    val landHistory = _landHistory.asStateFlow()

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    private val _actionType = MutableStateFlow("")
    val actionType = _actionType.asStateFlow()

    fun getAllLandHistory() {
        viewModelScope.launch {
            getLandHistoryUseCase()?.let {
                _landHistory.value = it
            }
        }
    }

    fun getLandHistoryByActionType() {
        viewModelScope.launch {
            getLandHistoryByActionTypeUseCase(_actionType.value)?.let {
                _landHistory.value = it
            }
        }
    }

    fun modifyActionType(actionType: String) {
        _actionType.value = actionType
    }
}