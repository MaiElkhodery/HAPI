package com.example.hapi.domain.usecase

import com.example.hapi.data.repository.LandownerRepository
import com.example.hapi.util.Crop
import javax.inject.Inject

class UploadSelectedCropUseCase @Inject constructor(
    private val landownerRepository: LandownerRepository
) {
    suspend operator fun invoke(
        crop: String
    ) = landownerRepository.uploadSelectedCrop(crop.lowercase())
}