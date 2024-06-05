package com.example.hapi.data.local.room.dao.farmer

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.hapi.data.local.room.entities.farmer.Farmer

@Dao
interface FarmerDao {

    @Upsert
    suspend fun addFarmer(farmers: Farmer)

    @Query("DELETE FROM Farmer")
    suspend fun deleteFarmers()

    @Query("SELECT * FROM Farmer")
    suspend fun getFarmers(): List<Farmer>
}