package com.example.hapi.data.local.room.entities.details

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DetectionDetails(
    @PrimaryKey(autoGenerate = true)
    val detectionId: Int,
    val name :String,
    val isHealthy: Boolean,
    val confidence: Float,
    val crop: String,
    val imagePath: String,
    val date: String,
    val time: String
)
