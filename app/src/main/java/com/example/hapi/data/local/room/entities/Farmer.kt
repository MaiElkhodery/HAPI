package com.example.hapi.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Farmer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)
