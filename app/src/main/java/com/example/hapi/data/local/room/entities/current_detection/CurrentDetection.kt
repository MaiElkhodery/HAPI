package com.example.hapi.data.local.room.entities.current_detection

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrentDetection(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val detectionMaker: String,
    val date: String,
    val time: String,
    val isHealthy: Boolean,
    val confidence: Float,
    val crop: String,
    val image: ByteArray,
)
