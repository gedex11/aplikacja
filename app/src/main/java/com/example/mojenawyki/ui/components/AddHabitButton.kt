package com.example.mojenawyki.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddHabitButton(
    onAddHabitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onAddHabitClick,
        modifier = modifier
    ) {
        Icon(Icons.Filled.Add, "Add Habit")
    }
}