package com.example.hapi.presentation.settings.farmers.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.landowner.FetchFarmersUseCase
import com.example.hapi.domain.usecase.landowner.GetFarmersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FarmersViewModel @Inject constructor(
    private val fetchFarmersUseCase: FetchFarmersUseCase,
    private val getFarmersUseCase: GetFarmersUseCase,
) : ViewModel() {

    private var _farmersList = MutableStateFlow(emptyList<String>())
    val farmersList = _farmersList.asStateFlow()

    suspend fun fetchFarmers() {
        fetchFarmersUseCase().collect { state ->
            when (state) {

                is State.Success -> {
                    _farmersList.value = state.result!!
                }

                else -> {
                }
            }
        }
    }

    suspend fun getFarmers() {
        _farmersList.value = getFarmersUseCase()
    }
}