package com.example.hapi.data.local.room.dao.current_detection

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.hapi.data.local.room.entities.current_detection.CurrentDetectionWithDisease
import com.example.hapi.data.local.room.entities.current_detection.CurrentDetection

@Dao
interface CurrentDetectionDao {
    @Upsert
    suspend fun insertDetection(detection: CurrentDetection): Long

    @Transaction
    @Query("SELECT * FROM CurrentDetection WHERE id = :id")
    suspend fun getDetectionById(id: Int): CurrentDetection

    @Transaction
    @Query("SELECT * FROM CurrentDetection WHERE id = :id")
    suspend fun getDetectionWithDiseasesById(id: Int): CurrentDetectionWithDisease

    @Query("DELETE FROM CurrentDetection")
    suspend fun deleteAll()
}