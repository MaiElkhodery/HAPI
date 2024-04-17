package com.example.hapi.data.local.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hapi.data.local.room.dao.details.DetectionDetailsDao
import com.example.hapi.data.local.room.dao.details.DiseaseDetailsDao
import com.example.hapi.data.local.room.dao.history.DetectionHistoryItemDao
import com.example.hapi.data.local.room.dao.history.DiseaseHistoryDao
import com.example.hapi.data.local.room.dao.landowner.LandownerDao
import com.example.hapi.data.local.room.dao.landowner.LandownerDetectionDao
import com.example.hapi.data.local.room.dao.landowner.LandownerDiseaseDao
import com.example.hapi.data.local.room.entities.details.DetectionDetails
import com.example.hapi.data.local.room.entities.details.DetectionDiseaseDetails
import com.example.hapi.data.local.room.entities.history.DetectionDiseaseHistory
import com.example.hapi.data.local.room.entities.history.DetectionHistoryItem
import com.example.hapi.data.local.room.entities.landowner.Landowner
import com.example.hapi.data.local.room.entities.landowner.LandownerDetection
import com.example.hapi.data.local.room.entities.landowner.LandownerDetectionDisease
import javax.inject.Singleton

@Singleton
@Database(
    entities = [(Landowner::class), (LandownerDetection::class), (LandownerDetectionDisease::class),
         (DetectionDiseaseDetails::class), (DetectionDetails::class),
        (DetectionHistoryItem::class), (DetectionDiseaseHistory::class)],
    version = 1,
    exportSchema = false
)
abstract class HapiDatabase : RoomDatabase() {
    abstract fun landownerDao(): LandownerDao
    abstract fun landownerDetectionDao(): LandownerDetectionDao

    abstract fun landownerDiseaseDao(): LandownerDiseaseDao

    abstract fun diseaseDetailsDao(): DiseaseDetailsDao

    abstract fun detectionDetailsDao(): DetectionDetailsDao

    abstract fun detectionHistoryItemDao(): DetectionHistoryItemDao

    abstract fun diseaseHistoryDao(): DiseaseHistoryDao

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