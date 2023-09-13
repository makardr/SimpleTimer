package com.example.simpletimer.feature_timer.domain.use_cases.service_use_cases

import com.example.simpletimer.feature_timer.domain.repository.TimerServiceRepository

class StartServiceUseCase (
    private val repository: TimerServiceRepository
) {
    operator fun invoke(data:String){
        repository.start(data)
    }
}
