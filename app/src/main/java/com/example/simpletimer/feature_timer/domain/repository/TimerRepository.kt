package com.example.simpletimer.feature_timer.domain.repository

import com.example.simpletimer.feature_timer.domain.model.TimerEntity
import kotlinx.coroutines.flow.Flow

interface TimerRepository {
    fun getTimers(): Flow<List<TimerEntity>>
    suspend fun getTimerById(id: Int): TimerEntity?
    suspend fun saveTimer(timerEntity: TimerEntity)
    suspend fun deleteTimer(timerEntity: TimerEntity)
}