package com.example.mojenawyki.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mojenawyki.ui.screens.AddHabitScreen
import com.example.mojenawyki.ui.screens.HabitListScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    viewModelFactory: HabitViewModelFactory
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HabitListScreen.route
    ) {
        composable(Screen.HabitListScreen.route) {
            val viewModel: HabitViewModel = viewModel(factory = viewModelFactory)
            HabitListScreen(
                viewModel = viewModel,
                onAddHabitClick = { navController.navigate(Screen.AddHabitScreen.route) },
                onHabitClick = { habit ->
                    //TODO
                }
            )
        }
        composable(Screen.AddHabitScreen.route) {
            val viewModel: HabitViewModel = viewModel(factory = viewModelFactory)
            AddHabitScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}