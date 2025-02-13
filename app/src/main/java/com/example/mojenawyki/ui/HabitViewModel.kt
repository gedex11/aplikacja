package com.example.mojenawyki.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mojenawyki.data.Habit
import com.example.mojenawyki.data.HabitRepository
import kotlinx.coroutines.launch

class HabitViewModel(private val repository: HabitRepository) : ViewModel() {
    val allHabits: LiveData<List<Habit>> = repository.getAllHabits().asLiveData()

    fun addHabit(habit: Habit) = viewModelScope.launch {
        repository.insert(habit)
    }

    fun insert(habit: Habit) = viewModelScope.launch {
        repository.insert(habit)
    }

    fun update(habit: Habit) = viewModelScope.launch {
        repository.update(habit)
    }

    fun delete(habit: Habit) = viewModelScope.launch {
        repository.delete(habit)
    }
}