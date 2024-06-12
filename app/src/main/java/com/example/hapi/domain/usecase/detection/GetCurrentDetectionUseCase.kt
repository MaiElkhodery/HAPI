package com.example.hapi.domain.usecase.detection

import com.example.hapi.data.repository.DiseaseDetectionRepository
import com.example.hapi.domain.model.DiseaseDetection
import javax.inject.Inject

class GetCurrentDetectionUseCase @Inject constructor(
    private val diseaseDetectionRepository: DiseaseDetectionRepository
) {
    suspend operator fun invoke(
        id: Int
    ): DiseaseDetection {
         val detection = diseaseDetectionRepository.getLocalCurrentDetectionById(id)
        return DiseaseDetection(
            id = detection.id,
            username = detection.username,
            date = detection.date,
            time = detection.time,
            certainty = detection.certainty,
            diseaseName = detection.diseaseName,
            link = detection.link,
            crop = detection.crop,
            image = detection.image
        )
    }

}