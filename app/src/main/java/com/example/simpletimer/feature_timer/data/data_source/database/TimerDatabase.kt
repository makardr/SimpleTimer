package com.example.simpletimer.feature_timer.data.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simpletimer.feature_timer.domain.model.TimerEntity


@Database(
    entities = [TimerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TimerDatabase: RoomDatabase() {
    abstract val timerDao: TimerDao

    companion object {
        const val DATABASE_NAME = "timers_db"
    }
}