package com.example.hapi.presentation.main

import androidx.lifecycle.ViewModel
import com.example.hapi.util.Tab
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
): ViewModel(){

    private var _selectedTab = MutableStateFlow(Tab.HOME)
    val selectedTab = _selectedTab.asStateFlow()

    fun setSelectedTab(tab: Tab){
        _selectedTab.value = tab
    }
}