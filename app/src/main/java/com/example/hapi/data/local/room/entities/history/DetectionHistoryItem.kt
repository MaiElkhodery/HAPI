package com.example.hapi.data.local.room.entities.history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DetectionHistoryItem(
    @PrimaryKey(autoGenerate = true)
    val detectionId: Int =0,
    val name :String,
    val imageByteArray: ByteArray,
    val date: String,
    val time: String,
    val isHealthy: Boolean,
    val confidence: Float
)
