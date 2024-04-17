package com.example.hapi.data.local.room.dao.details

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.hapi.data.local.room.entities.details.DetectionDetails
import com.example.hapi.data.local.room.entities.details.DetectionWithDiseases

@Dao
interface DetectionDetailsDao {
    @Upsert
    suspend fun insertDetection(detection: DetectionDetails): Long

    @Transaction
    @Query("SELECT * FROM DetectionDetails WHERE detectionId = :id")
    suspend fun getDetectionById(id: Int): DetectionDetails

    @Transaction
    @Query("SELECT * FROM DetectionDetails WHERE detectionId = :id")
    suspend fun getDetectionWithDiseasesId(id: Int): DetectionWithDiseases
}