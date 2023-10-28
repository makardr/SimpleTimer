package com.example.simpletimer.feature_timer.presentation.service

import android.app.Notification
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.ServiceLifecycleDispatcher
import androidx.lifecycle.lifecycleScope
import com.example.simpletimer.R
import com.example.simpletimer.core.values.Constants.INTENT_EXTRA_KEY
import com.example.simpletimer.core.values.Constants.NOTIFICATION_CHANNEL_ID
import com.example.simpletimer.feature_timer.domain.use_cases.database_use_cases.TimerUseCases
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class TimerForegroundService : LifecycleService() {

    @Inject
    lateinit var timerUseCases: TimerUseCases

    private val TAG = "TimerForegroundService"
    private val serviceLifecycleDispatcher = ServiceLifecycleDispatcher(this)

//    override fun onBind(intent: Intent): IBinder? {
//        return super.onBind(intent)
//    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {
            Actions.START.toString() -> startIntent(intent)
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startIntent(intent: Intent) {
        Log.d(TAG, "Start")
        val timerId = intent.getStringExtra(INTENT_EXTRA_KEY)
        startForeground(1, buildNotification(timerId))

        lifecycleScope.launch {
            if (timerId != null) {
                startTimer(timerId)
            }
        }
    }

    private suspend fun startTimer(timerId:String){
        val timerEntity =
            timerUseCases.getTimerByIdUseCase(timerId)
        timerEntity?.let {
            val timer = MillisecondsCountdownTimer(it.timeMS, 1000)
            timer.start()
            Log.d(TAG, "Timer should start")
        } ?: run {
            Log.e(TAG, "Timer entity is null")
        }
    }

    private fun buildNotification(data: String?): Notification {
        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Run is active")
            .setContentText(data)
            .build()
    }


    enum class Actions {
        START, STOP
    }
}