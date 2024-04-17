package com.example.hapi.data.local.room.dao.landowner

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.hapi.data.local.room.entities.landowner.Landowner

@Dao
interface LandownerDao {
    @Upsert
    suspend fun insertOrUpdateLandowner(landowner: Landowner)
    @Delete
    suspend fun deleteLandowner(landowner: Landowner)

    @Query("SELECT * FROM landowner WHERE id = 0")
    suspend fun getLandowner(): Landowner?
}