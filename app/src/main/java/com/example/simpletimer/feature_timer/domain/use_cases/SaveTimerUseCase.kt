package com.example.simpletimer.feature_timer.domain.use_cases

import com.example.simpletimer.feature_timer.domain.model.TimerEntity
import com.example.simpletimer.feature_timer.domain.repository.TimerRepository

class SaveTimerUseCase(
    private val repository: TimerRepository
) {
    suspend operator fun invoke(timerEntity: TimerEntity) {
        repository.saveTimer(timerEntity)
    }
}