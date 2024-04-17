package com.example.hapi.data.local.room.dao.history

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.hapi.data.local.room.entities.details.DetectionDiseaseDetails
import com.example.hapi.data.local.room.entities.history.DetectionDiseaseHistory

@Dao
interface DiseaseHistoryDao {
    @Upsert
    suspend fun insertDisease(disease: DetectionDiseaseHistory)

    @Insert
    suspend fun insertListOfDiseases(diseases: List<DetectionDiseaseHistory>)

    @Query("DELETE FROM DetectionDiseaseHistory")
    suspend fun deleteAll()
}