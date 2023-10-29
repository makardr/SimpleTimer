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
    //TODO Flow list with all timers from the timers database, should interact with it by adding timers to the database
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
                val timer = createTimer(timerId)
                timer?.start()
            }
        }
    }

    private suspend fun createTimer(timerId: String): SecondsCountdownTimer? {
        val timerEntity =
            timerUseCases.getTimerByIdUseCase(timerId)
        timerEntity?.let {
            Log.d(TAG, "Timer started")
            return SecondsCountdownTimer(it)
        } ?: run {
            Log.e(TAG, "Timer entity is null")
            return null
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