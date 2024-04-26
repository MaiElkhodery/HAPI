package com.example.hapi.presentation.home.landhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapi.data.local.room.entities.land_history.LandData
import com.example.hapi.domain.usecase.GetAllSavedLandHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandHistoryViewModel @Inject constructor(
    private val getLandDataUseCase: GetAllSavedLandHistoryUseCase
) : ViewModel() {

    private val _landDataList = MutableStateFlow(emptyList<LandData>())
    val landDataList = _landDataList.asStateFlow()

    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    fun getLandHistory() {
        viewModelScope.launch {
            getLandDataUseCase()?.let {
                _landDataList.value = it
            }
        }
    }

}