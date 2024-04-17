package com.example.hapi.data.local.room.dao.landowner

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.hapi.data.local.room.entities.landowner.LandownerDetectionDisease

@Dao
interface LandownerDiseaseDao {
    @Upsert
    suspend fun insertDisease(disease: LandownerDetectionDisease)

    @Insert
    suspend fun insertListOfDiseases(diseases: List<LandownerDetectionDisease>)

    @Query("DELETE FROM LandownerDetectionDisease")
    suspend fun deleteAll()
}