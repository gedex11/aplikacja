package com.example.mojenawyki.data

import kotlinx.coroutines.flow.Flow

class HabitRepository(private val habitDao: HabitDao) {
    fun getAllHabits(): Flow<List<Habit>> = habitDao.getAllHabits()
    suspend fun insert(habit: Habit) = habitDao.insert(habit)
    suspend fun update(habit: Habit) = habitDao.update(habit)
    suspend fun delete(habit: Habit) = habitDao.delete(habit)
}