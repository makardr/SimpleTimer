package com.example.simpletimer.feature_timer.domain.use_cases.database_use_cases

import com.example.simpletimer.feature_timer.domain.model.TimerEntity
import com.example.simpletimer.feature_timer.domain.repository.TimerRepository

class GetTimerByIdUseCase(
    private val repository: TimerRepository
) {
    suspend operator fun invoke(id: Int): TimerEntity? {
        return repository.getTimerById(id)
    }
}