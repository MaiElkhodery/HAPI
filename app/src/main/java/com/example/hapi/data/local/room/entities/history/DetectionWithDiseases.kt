package com.example.hapi.data.local.room.entities.history

import androidx.room.Embedded
import androidx.room.Relation

data class DetectionWithDiseases(
    @Embedded
    val detection: Detection,
    @Relation(
        parentColumn = "detectionId",
        entityColumn = "diseaseId"
    )
    val diseases: List<Disease>
)
