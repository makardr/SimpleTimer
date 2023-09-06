package com.example.simpletimer.feature_timer.domain.use_cases

data class TimerUseCases(
    val getTimersUseCase: GetTimersUseCase,
    val saveTimerUseCase: SaveTimerUseCase,
    val deleteTimerUseCase: DeleteTimerUseCase
)
