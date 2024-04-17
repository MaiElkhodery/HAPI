package com.example.hapi.data.local.room.entities.details

import androidx.room.Embedded
import androidx.room.Relation

data class DetectionWithDiseases(
    @Embedded
    val farmerDetection: DetectionDetails,
    @Relation(
        parentColumn = "detectionId",
        entityColumn = "diseaseId"
    )
    val diseases: List<DetectionDiseaseDetails>
)
