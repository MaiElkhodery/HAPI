package com.example.hapi.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hapi.data.local.room.dao.current_detection.CurrentDetectionDao
import com.example.hapi.data.local.room.dao.current_detection.CurrentDiseaseDao
import com.example.hapi.data.local.room.dao.detection_history.DetectionOfHistoryDao
import com.example.hapi.data.local.room.dao.land_history.LandDataDao
import com.example.hapi.data.local.room.entities.current_detection.CurrentDetection
import com.example.hapi.data.local.room.entities.current_detection.CurrentDetectionDisease
import com.example.hapi.data.local.room.entities.detection_history.DetectionOfHistory
import com.example.hapi.data.local.room.entities.land_history.LandData
import javax.inject.Singleton

@Singleton
@Database(
    entities = [(CurrentDetection::class), (CurrentDetectionDisease::class), (DetectionOfHistory::class),
        (LandData::class)],
    version = 1,
    exportSchema = false
)
abstract class HapiDatabase : RoomDatabase() {
    abstract fun currentDetectionDao(): CurrentDetectionDao

    abstract fun currentDiseaseDao(): CurrentDiseaseDao

    abstract fun detectionOfHistoryDao(): DetectionOfHistoryDao

    abstract fun landDataDao(): LandDataDao

}