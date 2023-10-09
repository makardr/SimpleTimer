package com.example.simpletimer.feature_timer.data.data_source.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simpletimer.feature_timer.domain.model.TimerEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TimerDao {
    @Query("SELECT * FROM timers_table")
    fun getTimers(): Flow<List<TimerEntity>>

    @Query("SELECT * FROM timers_table WHERE id = :id")
    suspend fun getTimerById(id: Int): TimerEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTimer(timerEntity: TimerEntity)

    @Delete
    suspend fun deleteTimer(timerEntity: TimerEntity)
}