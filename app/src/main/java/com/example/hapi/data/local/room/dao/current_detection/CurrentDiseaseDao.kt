package com.example.hapi.data.local.room.dao.current_detection

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.hapi.data.local.room.entities.current_detection.CurrentDetectionDisease

@Dao
interface CurrentDiseaseDao {
    @Upsert
    suspend fun insertDisease(disease: CurrentDetectionDisease)

    @Insert
    suspend fun insertListOfDiseases(diseases: List<CurrentDetectionDisease>)

    @Query("DELETE FROM CurrentDetectionDisease")
    suspend fun deleteAll()
}