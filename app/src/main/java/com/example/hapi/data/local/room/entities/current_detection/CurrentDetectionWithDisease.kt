package com.example.hapi.data.local.room.entities.current_detection

import androidx.room.Embedded
import androidx.room.Relation

data class CurrentDetectionWithDisease(
    @Embedded val detection: CurrentDetection,
    @Relation(
        parentColumn = "id",
        entityColumn = "detectionId"
    )
    val diseases: List<CurrentDetectionDisease>
)
