package com.example.simpletimer.feature_timer.data.data_source.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.simpletimer.feature_timer.domain.use_cases.database_use_cases.TimerUseCases
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class TimerForegroundService : Service() {

    @Inject
    lateinit var timerUseCases: TimerUseCases

    private val TAG = "TimerForegroundService"
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> startIntent()
            Actions.STOP.toString() -> stopSelf()
        }

        return super.onStartCommand(intent, flags, startId)
    }


    private fun startIntent() {
        Log.d(TAG, "Start")
    }


    enum class Actions {
        START, STOP
    }
}