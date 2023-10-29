package com.example.simpletimer.feature_timer.presentation.service

import android.os.CountDownTimer
import android.util.Log
import com.example.simpletimer.feature_timer.domain.model.TimerEntity

class SecondsCountdownTimer(timerEntity: TimerEntity) :
    CountDownTimer(timerEntity.timeMS, 1000) {
    val TAG = "SecondsCountdownTimer"
    override fun onTick(msUntillFinished: Long) {
        Log.d(TAG, "Seconds remaining $msUntillFinished")
    }

    override fun onFinish() {
        Log.d(TAG, "Timer finished")
    }
}