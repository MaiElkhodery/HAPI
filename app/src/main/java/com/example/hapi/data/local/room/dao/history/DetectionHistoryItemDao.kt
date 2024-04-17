package com.example.hapi.data.local.room.dao.history

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.hapi.data.local.room.entities.history.DetectionHistoryItem
import com.example.hapi.data.local.room.entities.history.DetectionHistoryWithDiseases

@Dao
interface DetectionHistoryItemDao {
    @Upsert
    suspend fun insertDetectionList(detection: DetectionHistoryItem): Long

    @Upsert
    suspend fun insertDetectionList(detection: List<DetectionHistoryItem>)

    @Query("SELECT * FROM DetectionHistoryItem WHERE detectionId = :id")
    suspend fun getDetectionById(id: Int): DetectionHistoryItem

    @Transaction
    @Query("SELECT * FROM DetectionHistoryItem WHERE detectionId = :id")
    suspend fun getDetectionWithDiseasesId(id: Int): DetectionHistoryWithDiseases

    @Transaction
    @Query("SELECT * FROM DetectionHistoryItem")
    suspend fun getALlDetectionWithDiseases(): List<DetectionHistoryWithDiseases>

    @Query("DELETE FROM DetectionHistoryItem")
    suspend fun deleteAll()
}