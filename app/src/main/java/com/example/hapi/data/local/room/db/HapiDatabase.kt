package com.example.hapi.data.local.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hapi.data.local.room.dao.current_detection.CurrentDetectionDao
import com.example.hapi.data.local.room.dao.current_detection.CurrentDiseaseDao
import com.example.hapi.data.local.room.dao.detection_history.DetectionOfHistoryDao
import com.example.hapi.data.local.room.entities.current_detection.CurrentDetection
import com.example.hapi.data.local.room.entities.current_detection.CurrentDetectionDisease
import com.example.hapi.data.local.room.entities.detection_history.DetectionOfHistory
import javax.inject.Singleton

@Singleton
@Database(
    entities = [(CurrentDetection::class), (CurrentDetectionDisease::class), (DetectionOfHistory::class)],
    version = 1,
    exportSchema = false
)
abstract class HapiDatabase : RoomDatabase() {
    abstract fun currentDetectionDao(): CurrentDetectionDao

    abstract fun currentDiseaseDao(): CurrentDiseaseDao

    abstract fun detectionOfHistoryDao(): DetectionOfHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HapiDatabase? = null

        fun getInstance(context: Context): HapiDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HapiDatabase::class.java,
                    "landowner_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}