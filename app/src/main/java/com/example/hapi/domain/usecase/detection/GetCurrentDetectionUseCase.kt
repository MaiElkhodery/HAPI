package com.example.hapi.domain.usecase.detection

import com.example.hapi.data.repository.DiseaseDetectionRepository
import com.example.hapi.domain.model.CurrentDiseaseDetection
import javax.inject.Inject

class GetCurrentDetectionUseCase @Inject constructor(
    private val diseaseDetectionRepository: DiseaseDetectionRepository
) {
    suspend operator fun invoke(
        id: Int
    ): CurrentDiseaseDetection {
         val detection = diseaseDetectionRepository.getLocalCurrentDetectionById(id)
        return CurrentDiseaseDetection(
            id = detection.id,
            username = detection.username,
            date = detection.date,
            time = detection.time,
            certainty = detection.certainty,
            diseaseName = detection.diseaseName,
            link = detection.link,
            crop = detection.crop,
            imageLocalUri = detection.imageLocalUrl
        )
    }

}