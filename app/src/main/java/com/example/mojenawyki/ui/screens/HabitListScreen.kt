package com.example.mojenawyki.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.mojenawyki.data.Habit
import com.example.mojenawyki.ui.HabitViewModel
import com.example.mojenawyki.ui.components.AddHabitButton
import com.example.mojenawyki.ui.components.HabitItem
import com.example.mojenawyki.utils.NotificationHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitListScreen(
    viewModel: HabitViewModel,
    onAddHabitClick: () -> Unit,
    onHabitClick: (Habit) -> Unit
) {
    val habits: List<Habit> by viewModel.allHabits.observeAsState(initial = emptyList())
    val context = LocalContext.current
    val notificationHelper = NotificationHelper(context)

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            notificationHelper.showNotification(
                "Przypomnienie o nawyku",
                "Pamiętaj o swoim nawyku!"
            )
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Moje Nawyki") })
        },
        floatingActionButton = {
            AddHabitButton(onAddHabitClick = onAddHabitClick)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (habits.isEmpty()) {
                Text(
                    text = "No habits yet!",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                Column(modifier = Modifier.padding(innerPadding)) {
                    Button(
                        onClick = {
                            if (ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.POST_NOTIFICATIONS
                                ) != PackageManager.PERMISSION_GRANTED
                            ) {
                                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                            } else {
                                notificationHelper.showNotification(
                                    "Przypomnienie o nawyku",
                                    "Pamiętaj o swoim nawyku!"
                                )
                            }
                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Pokaż powiadomienie")
                    }
                    LazyColumn(modifier = Modifier.padding(8.dp)) {
                        items(habits) { habit ->
                            HabitItem(
                                habit = habit,
                                onHabitClick = onHabitClick,
                                onCheckedChange = { isChecked ->
                                    viewModel.update(habit.copy(completed = isChecked))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}