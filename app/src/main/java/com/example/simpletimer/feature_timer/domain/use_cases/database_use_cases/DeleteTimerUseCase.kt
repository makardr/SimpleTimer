package com.example.simpletimer.feature_timer.domain.use_cases.database_use_cases

import com.example.simpletimer.feature_timer.domain.model.TimerEntity
import com.example.simpletimer.feature_timer.domain.repository.TimerRepository

class DeleteTimerUseCase(
    private val repository: TimerRepository
) {
    suspend fun invoke(timerEntity: TimerEntity) {
        repository.deleteTimer(timerEntity)
    }
}