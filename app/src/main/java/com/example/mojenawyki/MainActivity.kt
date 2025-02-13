package com.example.mojenawyki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mojenawyki.data.HabitRepository
import com.example.mojenawyki.ui.AppNavGraph
import com.example.mojenawyki.ui.HabitViewModelFactory
import com.example.mojenawyki.ui.theme.ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = (application as HabitApplication).database
        val repository = HabitRepository(database.habitDao())
        val viewModelFactory = HabitViewModelFactory(repository)

        setContent {
            ProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavGraph(viewModelFactory = viewModelFactory)
                }
            }
        }
    }
}