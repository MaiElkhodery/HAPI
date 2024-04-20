package com.example.hapi.data.local.room.entities.detection_history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DetectionOfHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remoteId: Int,
    val username: String,
    val date: String,
    val time: String,
    val imageUrl: String
)
