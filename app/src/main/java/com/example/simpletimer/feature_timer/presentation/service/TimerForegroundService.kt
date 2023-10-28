package com.example.simpletimer.feature_timer.presentation.service

import android.app.Notification
import android.content.Intent
import android.os.IBinder
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

    override fun onBind(intent: Intent): IBinder? {
        return super.onBind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {
            Actions.START.toString() -> startIntent(intent)
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startIntent(intent: Intent) {
        Log.d(TAG, "Start")
        startForeground(1, buildNotification(intent.getStringExtra(INTENT_EXTRA_KEY)))
        startTimerFromDb(intent.getStringExtra(INTENT_EXTRA_KEY))

    }

    private fun buildNotification(data: String?): Notification {
        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Run is active")
            .setContentText(data)
            .build()
    }

    // Move this function to the use case
    private fun startTimerFromDb(id: String?) {
        if (id != null) {
            lifecycleScope.launch {
                Log.d(TAG, "Attempting to start timer id $id")
                val timerEntity = timerUseCases.getTimerByIdUseCase(id.toInt())
                timerEntity?.let {
                    val timer = MillisecondsCountdownTimer(it.timeMS, 1000)
                    timer.start()
                    Log.d(TAG, "Timer should be started")
                } ?: run {
                    Log.e(TAG, "Timer entity is null")
                }

            }
        } else {
            Log.e(TAG, "id was null")
        }

    }


    enum class Actions {
        START, STOP
    }
}