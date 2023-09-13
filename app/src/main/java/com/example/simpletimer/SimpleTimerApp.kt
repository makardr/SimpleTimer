package com.example.simpletimer

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import com.example.simpletimer.core.values.Constants.NOTIFICATION_CHANNEL_ID
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SimpleTimerApp : Application() {
    private val TAG = "SimpleTimerApp"
    override fun onCreate() {
        super.onCreate()
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Timer notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    init {
        Log.d(TAG,"Initialized")
        instance = this
    }
    companion object {
        private var instance: Application? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}