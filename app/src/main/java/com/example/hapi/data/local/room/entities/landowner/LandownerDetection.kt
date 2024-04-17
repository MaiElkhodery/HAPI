package com.example.hapi.data.local.room.entities.landowner

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LandownerDetection(
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
