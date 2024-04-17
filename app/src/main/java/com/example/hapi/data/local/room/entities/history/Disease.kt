package com.example.hapi.data.local.room.entities.history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Disease(
    @PrimaryKey(autoGenerate = true)
    val diseaseId: Int = 0,
    val name: String,
    val confidence: Double,
    val infoLink: String,
    val detectionId: Int
)

