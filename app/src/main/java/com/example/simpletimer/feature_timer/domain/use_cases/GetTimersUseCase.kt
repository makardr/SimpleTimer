package com.example.simpletimer.feature_timer.domain.use_cases

import com.example.simpletimer.feature_timer.domain.model.TimerEntity
import com.example.simpletimer.feature_timer.domain.repository.TimerRepository
import kotlinx.coroutines.flow.Flow

class GetTimersUseCase(
    private val repository: TimerRepository
) {
    operator fun invoke(): Flow<List<TimerEntity>> {
        return repository.getTimers()
    }
}