package com.example.hapi.domain.usecase.landowner

import com.example.hapi.data.repository.LandownerRepository
import javax.inject.Inject

class UploadSelectedCropUseCase @Inject constructor(
    private val landownerRepository: LandownerRepository
) {
    suspend operator fun invoke(
        crop: String
    ) = landownerRepository.uploadSelectedCrop(crop.lowercase())
}