package com.example.hapi.data.local.room.entities.landowner

import androidx.room.Embedded
import androidx.room.Relation

data class LandownerDetectionWithDiseases(
    @Embedded val detection: LandownerDetection,
    @Relation(
        parentColumn = "id",
        entityColumn = "detectionId"
    )
    val diseases: List<LandownerDetectionDisease>
)
