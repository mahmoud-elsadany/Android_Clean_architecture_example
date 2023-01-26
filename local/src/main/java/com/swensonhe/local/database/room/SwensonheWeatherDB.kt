package com.swensonhe.local.database.room

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.swensonhe.local.database.Converters
import com.swensonhe.local.model.*
import com.syngenta.local.database.room.TestDAO

@Database(
    entities = [TestEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class SwensonheWeatherDB : RoomDatabase() {

    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "swensonhe_weather.db"

        @Volatile
        private var INSTANCE: SwensonheWeatherDB? = null

        fun getInstance(@NonNull context: Context): SwensonheWeatherDB {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            SwensonheWeatherDB::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun getTestDAO(): TestDAO

}

