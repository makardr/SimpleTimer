package com.example.simpletimer.feature_timer.data.repository

import android.content.Context
import android.content.Intent
import com.example.simpletimer.core.values.Constants.INTENT_EXTRA_KEY
import com.example.simpletimer.feature_timer.presentation.service.TimerForegroundService
import com.example.simpletimer.feature_timer.domain.repository.TimerServiceRepository

class TimerServiceRepositoryImpl(
    private val context: Context
) : TimerServiceRepository {


    override fun start(data: String) {
        Intent(context, TimerForegroundService::class.java).also {
            it.action = TimerForegroundService.Actions.START.toString()
            it.putExtra(INTENT_EXTRA_KEY, data)
            context.startService(it)
        }
    }

    override fun stop() {
        Intent(context, TimerForegroundService::class.java).also {
            it.action = TimerForegroundService.Actions.STOP.toString()
            context.startService(it)
        }
    }
}