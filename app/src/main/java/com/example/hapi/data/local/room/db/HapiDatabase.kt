package com.example.hapi.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hapi.data.local.room.dao.CurrentDetectionDao
import com.example.hapi.data.local.room.dao.DetectionOfHistoryDao
import com.example.hapi.data.local.room.dao.FarmerDao
import com.example.hapi.data.local.room.dao.LandDataDao
import com.example.hapi.data.local.room.entities.CurrentDetection
import com.example.hapi.data.local.room.entities.DetectionOfHistory
import com.example.hapi.data.local.room.entities.Farmer
import com.example.hapi.data.local.room.entities.LandData
import javax.inject.Singleton

@Singleton
@Database(
    entities = [(CurrentDetection::class), (DetectionOfHistory::class),
        (LandData::class), (Farmer::class)],
    version = 1,
    exportSchema = false
)
abstract class HapiDatabase : RoomDatabase() {
    abstract fun currentDetectionDao(): CurrentDetectionDao

    abstract fun detectionOfHistoryDao(): DetectionOfHistoryDao

    abstract fun landDataDao(): LandDataDao

    abstract fun farmerDao(): FarmerDao

}