package com.example.simpletimer.feature_timer.data.data_source.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.simpletimer.R
import com.example.simpletimer.core.values.Constants.INTENT_EXTRA_KEY
import com.example.simpletimer.core.values.Constants.NOTIFICATION_CHANNEL_ID
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
            Actions.START.toString() -> startIntent(intent)
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }


    private fun startIntent(intent: Intent) {
        Log.d(TAG, "Start")
        startForeground(1, buildNotification(intent.getStringExtra(INTENT_EXTRA_KEY)))
    }

    private fun buildNotification(data: String?):Notification{
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