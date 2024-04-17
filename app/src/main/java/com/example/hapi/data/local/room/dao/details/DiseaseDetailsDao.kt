package com.example.hapi.data.local.room.dao.details

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Upsert
import com.example.hapi.data.local.room.entities.details.DetectionDiseaseDetails

@Dao
interface DiseaseDetailsDao {
    @Upsert
    suspend fun insertDisease(disease: DetectionDiseaseDetails)

    @Insert
    suspend fun insertListOfDiseases(diseases: List<DetectionDiseaseDetails>)
}