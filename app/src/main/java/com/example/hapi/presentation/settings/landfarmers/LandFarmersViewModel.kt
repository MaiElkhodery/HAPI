package com.example.hapi.presentation.settings.landfarmers

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hapi.domain.model.State
import com.example.hapi.domain.usecase.landowner.FetchFarmersUseCase
import com.example.hapi.domain.usecase.landowner.GetFarmersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LandFarmersViewModel @Inject constructor(
    private val fetchFarmersUseCase: FetchFarmersUseCase,
    private val getFarmersUseCase: GetFarmersUseCase,
) : ViewModel() {

    private var _farmersList = MutableStateFlow(emptyList<String>())
    val farmersList = _farmersList.asStateFlow()

    suspend fun fetchFarmers() {
        fetchFarmersUseCase().collect { state ->
            when (state) {
                is State.Error -> {
                    Log.d("FarmersListViewModel", "Error")
                }

                is State.Exception -> {
                    Log.d("FarmersListViewModel", "Exception")
                }

                State.Loading -> {
                    Log.d("FarmersListViewModel", "Loading")
                }

                is State.Success -> {
                    _farmersList.value = state.result!!
                    Log.d("FarmersListViewModel", "Success")
                }
            }
        }
    }

    suspend fun getFarmers() {
        _farmersList.value = getFarmersUseCase()
    }
}