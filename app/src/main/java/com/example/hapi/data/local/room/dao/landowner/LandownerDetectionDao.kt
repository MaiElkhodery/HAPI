package com.example.hapi.data.local.room.dao.landowner

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.hapi.data.local.room.entities.landowner.LandownerDetectionWithDiseases
import com.example.hapi.data.local.room.entities.landowner.LandownerDetection

@Dao
interface LandownerDetectionDao {
    @Upsert
    suspend fun insertDetection(detection: LandownerDetection): Long

    @Transaction
    @Query("SELECT * FROM LandownerDetection WHERE id = :id")
    suspend fun getDetectionById(id: Int): LandownerDetection

    @Transaction
    @Query("SELECT * FROM LandownerDetection WHERE id = :id")
    suspend fun getDetectionWithDiseasesById(id: Int): LandownerDetectionWithDiseases

    @Query("DELETE FROM LandownerDetection")
    suspend fun deleteAll()
}