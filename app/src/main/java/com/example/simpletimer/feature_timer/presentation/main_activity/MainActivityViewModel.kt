package com.example.simpletimer.feature_timer.presentation.main_activity

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.simpletimer.feature_timer.domain.use_cases.database_use_cases.TimerUseCases
import com.example.simpletimer.feature_timer.domain.use_cases.service_use_cases.ServiceUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val timerUseCases: TimerUseCases,
    private val serviceUseCases: ServiceUseCases
) : ViewModel() {
    val TAG = "MainActivityViewModel"
    init {
        Log.d(TAG,"Initialized")

    }
    fun test(){
        serviceUseCases.startServiceUseCase()
    }
}