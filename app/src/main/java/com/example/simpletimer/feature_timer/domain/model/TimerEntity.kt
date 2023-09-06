package com.example.simpletimer.feature_timer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simpletimer.core.values.Constants

@Entity(tableName = Constants.PREFERENCES_TABLE_NAME)
data class TimerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val timeMS: Long
)