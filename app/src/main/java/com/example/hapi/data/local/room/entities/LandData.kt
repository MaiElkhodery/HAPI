package com.example.hapi.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LandData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remote_id: Int,
    val date: String,
    val time: String,
    val action_type: String,
)
