package com.example.simpletimer.feature_timer.domain.repository

import android.app.Service
import android.content.Context

interface TimerServiceRepository {
    fun start(data: String)
    fun stop()
}