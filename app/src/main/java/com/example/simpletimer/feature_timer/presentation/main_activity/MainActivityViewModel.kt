package com.example.simpletimer.feature_timer.presentation.main_activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletimer.feature_timer.domain.model.TimerEntity
import com.example.simpletimer.feature_timer.domain.use_cases.database_use_cases.TimerUseCases
import com.example.simpletimer.feature_timer.domain.use_cases.service_use_cases.ServiceUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    fun startService(data: String){
        serviceUseCases.startServiceUseCase(data)
    }

    fun stopService(){
        serviceUseCases.stopServiceUseCase()
    }

    fun addTimer(timeString: String, timerTitle: String){
        viewModelScope.launch{
            timerUseCases.saveTimerUseCase(TimerEntity(timeMS = timeString.toLong(), title = timerTitle))
        }
    }

}