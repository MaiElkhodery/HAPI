package com.example.hapi.data.local.room.dao.detection_history

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hapi.data.local.room.entities.detection_history.DetectionOfHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface DetectionOfHistoryDao {
    @Upsert
    suspend fun insertDetection(detection: DetectionOfHistory): Long

    @Upsert
    suspend fun insertDetectionList(detection: List<DetectionOfHistory>)

    @Query("SELECT * FROM DetectionOfHistory WHERE id = :id")
    suspend fun getDetectionById(id: Int): DetectionOfHistory

    @Query("DELETE FROM DetectionOfHistory")
    suspend fun deleteAll()

    @Query("SELECT * FROM DetectionOfHistory")
    suspend fun getAllDetectionHistory(): List<DetectionOfHistory>

    @Query("SELECT * FROM DetectionOfHistory ORDER BY id DESC LIMIT 1")
    suspend fun getNewestDetection(): DetectionOfHistory
}