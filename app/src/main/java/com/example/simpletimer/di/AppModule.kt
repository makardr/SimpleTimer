package com.example.simpletimer.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.simpletimer.SimpleTimerApp
import com.example.simpletimer.feature_timer.data.data_source.database.TimerDatabase
import com.example.simpletimer.feature_timer.data.repository.TimerDatabaseRepositoryImpl
import com.example.simpletimer.feature_timer.data.repository.TimerServiceRepositoryImpl
import com.example.simpletimer.feature_timer.domain.repository.TimerRepository
import com.example.simpletimer.feature_timer.domain.repository.TimerServiceRepository
import com.example.simpletimer.feature_timer.domain.use_cases.database_use_cases.DeleteTimerUseCase
import com.example.simpletimer.feature_timer.domain.use_cases.database_use_cases.GetTimerByIdUseCase
import com.example.simpletimer.feature_timer.domain.use_cases.database_use_cases.GetTimersUseCase
import com.example.simpletimer.feature_timer.domain.use_cases.database_use_cases.SaveTimerUseCase
import com.example.simpletimer.feature_timer.domain.use_cases.database_use_cases.TimerUseCases
import com.example.simpletimer.feature_timer.domain.use_cases.service_use_cases.ServiceUseCases
import com.example.simpletimer.feature_timer.domain.use_cases.service_use_cases.StartServiceUseCase
import com.example.simpletimer.feature_timer.domain.use_cases.service_use_cases.StopServiceUseCase
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
        return TimerDatabaseRepositoryImpl(database.timerDao)
    }

    @Provides
    @Singleton
    fun provideTimerUseCases(repository: TimerRepository): TimerUseCases {
        return TimerUseCases(
            getTimersUseCase = GetTimersUseCase(repository),
            saveTimerUseCase = SaveTimerUseCase(repository),
            deleteTimerUseCase = DeleteTimerUseCase(repository),
            getTimerByIdUseCase = GetTimerByIdUseCase(repository)
        )
    }
    @Provides
    @Singleton
    fun provideAppContext(): Context {
        return SimpleTimerApp.applicationContext()
    }

    @Provides
    @Singleton
    fun provideServiceRepository(context: Context): TimerServiceRepository{
        return TimerServiceRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideServiceUseCases(timerServiceRepository: TimerServiceRepository): ServiceUseCases{
        return ServiceUseCases(
            startServiceUseCase = StartServiceUseCase(timerServiceRepository),
            stopServiceUseCase = StopServiceUseCase(timerServiceRepository)
        )
    }
}