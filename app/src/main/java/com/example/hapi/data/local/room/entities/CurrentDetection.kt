package com.example.hapi.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrentDetection(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val date: String,
    val time: String,
    val certainty: Float,
    val diseaseName : String,
    val link : String,
    val crop: String,
    val imageLocalUrl: String,
)
