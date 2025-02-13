package com.example.mojenawyki.ui

sealed class Screen(val route: String) {
    object HabitListScreen : Screen("habit_list_screen")
    object AddHabitScreen : Screen("add_habit_screen")
}