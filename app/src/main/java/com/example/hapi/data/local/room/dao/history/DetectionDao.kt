package com.example.hapi.data.local.room.dao.history

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.hapi.data.local.room.entities.history.Detection
import com.example.hapi.data.local.room.entities.history.DetectionWithDiseases

@Dao
interface DetectionDao {
    @Upsert
    suspend fun insertDetectionList(detection: Detection): Long

    @Upsert
    suspend fun insertDetectionList(detection: List<Detection>)

    @Query("SELECT * FROM Detection WHERE detectionId = :id")
    suspend fun getDetectionById(id: Int): Detection

    @Transaction
    @Query("SELECT * FROM Detection WHERE detectionId = :id")
    suspend fun getDetectionWithDiseasesId(id: Int): DetectionWithDiseases

    @Transaction
    @Query("SELECT * FROM Detection")
    suspend fun getALlDetectionWithDiseases(): List<DetectionWithDiseases>

    @Query("DELETE FROM Detection")
    suspend fun deleteAll()
}