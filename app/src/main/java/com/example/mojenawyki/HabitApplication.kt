package com.example.mojenawyki

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.room.Room
import com.example.mojenawyki.data.HabitDatabase

class HabitApplication : Application() {

    lateinit var database: HabitDatabase

    override fun onCreate() {
        super.onCreate()
        database = provideHabitDatabase(this)
        createNotificationChannel()
    }

    private fun provideHabitDatabase(application: Application): HabitDatabase {
        return Room.databaseBuilder(
            application,
            HabitDatabase::class.java,
            "habit_database"
        ).build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Habit Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for habit reminders"
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "habit_channel"
    }
}