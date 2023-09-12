package com.example.simpletimer.feature_timer.data.data_source.service

import android.os.CountDownTimer
import android.util.Log

class MillisecondsCountdownTimer(ms:Long,countdownInterval:Long) : CountDownTimer(ms,countdownInterval){
    val TAG = "Timer"
    override fun onTick(msUntillFinished: Long) {
        Log.d(TAG, "Timer is " + msUntillFinished)
    }

    override fun onFinish() {
        Log.d(TAG, "Timer finished")
    }
}