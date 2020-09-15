package com.example.weather.business.database

import android.content.Context
import androidx.room.*
import com.example.weather.business.model.Weather

@Database(entities = [Weather::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        var INSTANCE: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase? {
            if (INSTANCE == null) {
                synchronized(WeatherDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, WeatherDatabase::class.java, "weather.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}