package com.example.simpletimer.feature_timer.presentation.main_activity

import androidx.lifecycle.ViewModel
import com.example.simpletimer.feature_timer.domain.use_cases.DeleteTimerUseCase
import com.example.simpletimer.feature_timer.domain.use_cases.TimerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val timerUseCases: TimerUseCases
) : ViewModel() {
    init {

    }
}