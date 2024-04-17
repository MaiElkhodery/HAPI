package com.example.hapi.data.local.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hapi.data.local.room.dao.current_detection.CurrentDiseaseDao
import com.example.hapi.data.local.room.dao.current_detection.CurrrentDetectionDao
import com.example.hapi.data.local.room.dao.history.DetectionDao
import com.example.hapi.data.local.room.dao.history.DiseaseDao
import com.example.hapi.data.local.room.entities.current_detection.CurrentDetection
import com.example.hapi.data.local.room.entities.current_detection.CurrentDetectionDisease
import com.example.hapi.data.local.room.entities.history.Detection
import com.example.hapi.data.local.room.entities.history.Disease
import javax.inject.Singleton

@Singleton
@Database(
    entities = [(CurrentDetection::class), (CurrentDetectionDisease::class),
        (Detection::class), (Disease::class)],
    version = 1,
    exportSchema = false
)
abstract class HapiDatabase : RoomDatabase() {
    abstract fun currentDetectionDao(): CurrrentDetectionDao

    abstract fun currentDiseaseDao(): CurrentDiseaseDao

    abstract fun detectionDao(): DetectionDao

    abstract fun diseaseDao(): DiseaseDao

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