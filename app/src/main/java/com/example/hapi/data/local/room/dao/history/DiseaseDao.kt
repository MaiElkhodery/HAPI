package com.example.hapi.data.local.room.dao.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.hapi.data.local.room.entities.history.Disease

@Dao
interface DiseaseDao {
    @Upsert
    suspend fun insertDisease(disease: Disease)

    @Insert
    suspend fun insertListOfDiseases(diseases: List<Disease>)

    @Query("DELETE FROM Disease")
    suspend fun deleteAll()
}