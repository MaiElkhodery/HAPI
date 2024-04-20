package com.example.hapi.domain.usecase

import android.util.Log
import com.example.hapi.domain.model.State
import com.example.hapi.data.repository.LandownerRepository
import com.example.hapi.util.Crop
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CropRecommendationUseCase @Inject constructor(
    private val landownerRepository: LandownerRepository
) {

    suspend operator fun invoke(): Flow<State<List<Crop>>> {
        return flow {
            landownerRepository.cropRecommendation().collect { state ->
                when (state) {

                    is State.Loading -> {
                        emit(State.Loading)
                        Log.d("CropRecommendation", "Loading...")

                    }

                    is State.Success -> {
                        val result = state.result
                        var crops = listOf(
                            Crop.POTATO to result!!.potato,
                            Crop.COTTON to result.cotton,
                            Crop.TOMATO to result.tomato,
                            Crop.SUGARCANE to result.sugarcane,
                            Crop.CORN to result.corn,
                            Crop.WHEAT to result.wheat,
                            Crop.APPLE to result.apple
                        )
                        crops = crops.sortedByDescending { it.second }
                        val topRecommendedCrops = crops.take(3).map { it.first }
                        Log.d("CropRecommendation", topRecommendedCrops.toString())
                        emit(State.Success(topRecommendedCrops))

                    }

                    is State.Error -> {
                        emit(State.Error(state.error))
                        Log.d("CropRecommendation", "Error: ${state.error}")
                    }

                    else -> {}
                }
            }
        }
    }
}