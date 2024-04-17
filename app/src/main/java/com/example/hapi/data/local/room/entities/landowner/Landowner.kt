package com.example.hapi.data.local.room.entities.landowner

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "landowner")
data class Landowner(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val landId: String,
    val crop: String? = null
)