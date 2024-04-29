package com.example.hapi.presentation.settings.landowner

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LandownerSettingsViewModel @Inject constructor(): ViewModel() {

    private val _landId = MutableStateFlow("")
    val landId = _landId.asStateFlow()
}