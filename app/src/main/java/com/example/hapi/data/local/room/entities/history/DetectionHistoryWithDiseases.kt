package com.example.hapi.data.local.room.entities.history

import androidx.room.Embedded
import androidx.room.Relation

data class DetectionHistoryWithDiseases(
    @Embedded
    val detection: DetectionHistoryItem,
    @Relation(
        parentColumn = "detectionId",
        entityColumn = "diseaseId"
    )
    val diseases: List<DetectionDiseaseHistory>
)
