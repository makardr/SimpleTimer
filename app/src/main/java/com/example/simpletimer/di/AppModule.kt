package com.example.simpletimer.di

import android.app.Application
import androidx.room.Room
import com.example.simpletimer.feature_timer.data.data_source.TimerDatabase
import com.example.simpletimer.feature_timer.data.repository.TimerRepositoryImpl
import com.example.simpletimer.feature_timer.domain.repository.TimerRepository
import com.example.simpletimer.feature_timer.domain.use_cases.DeleteTimerUseCase
import com.example.simpletimer.feature_timer.domain.use_cases.GetTimersUseCase
import com.example.simpletimer.feature_timer.domain.use_cases.SaveTimerUseCase
import com.example.simpletimer.feature_timer.domain.use_cases.TimerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTimerDatabase(app: Application): TimerDatabase {
        return Room.databaseBuilder(
            app,
            TimerDatabase::class.java,
            TimerDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideTimerRepository(database: TimerDatabase): TimerRepository {
        return TimerRepositoryImpl(database.timerDao)
    }

    @Provides
    @Singleton
    fun provideTimerUseCases(repository: TimerRepository): TimerUseCases {
        return TimerUseCases(
            getTimersUseCase = GetTimersUseCase(repository),
            saveTimerUseCase = SaveTimerUseCase(repository),
            deleteTimerUseCase = DeleteTimerUseCase(repository)
        )
    }
}