package com.example.simpletimer.feature_timer.data.repository

import com.example.simpletimer.feature_timer.data.data_source.database.TimerDao
import com.example.simpletimer.feature_timer.domain.model.TimerEntity
import com.example.simpletimer.feature_timer.domain.repository.TimerRepository
import kotlinx.coroutines.flow.Flow

class TimerDatabaseRepositoryImpl(
    private val dao: TimerDao
) : TimerRepository {
    override fun getTimers(): Flow<List<TimerEntity>> {
        return dao.getTimers()
    }

    override suspend fun getTimerById(id: Int): TimerEntity? {
        return dao.getTimerById(id)
    }

    override suspend fun saveTimer(timerEntity: TimerEntity) {
        dao.saveTimer(timerEntity)
    }

    override suspend fun deleteTimer(timerEntity: TimerEntity) {
        dao.deleteTimer(timerEntity)
    }
}