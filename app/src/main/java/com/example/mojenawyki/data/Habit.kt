package com.example.mojenawyki.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val frequency: String, // np. "daily", "every other day"
    val time: String, // np. "10:00"
    var completed: Boolean = false,
    val imagePath: String? = null
)