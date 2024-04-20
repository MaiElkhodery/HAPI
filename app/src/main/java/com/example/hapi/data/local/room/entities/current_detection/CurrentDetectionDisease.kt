package com.example.hapi.data.local.room.entities.current_detection

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrentDetectionDisease(
    @PrimaryKey(autoGenerate = true)
    val detectionDiseaseId: Int = 0,
    val name: String,
    val confidence: Double,
    val infoLink: String,
    val detectionId: Int
)

