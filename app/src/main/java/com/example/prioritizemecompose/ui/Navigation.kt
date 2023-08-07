package com.example.prioritizemecompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prioritizemecompose.ui.screens.AddTaskScreen
import com.example.prioritizemecompose.ui.screens.TaskListScreen
import com.example.prioritizemecompose.ui.screens.UpdateTaskScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.TaskList.route) {
        composable(route = Screens.TaskList.route){
            TaskListScreen (
                onAddScreen = {navController.navigate(Screens.Add.route) },
                onEditScreen = { navController.navigate(Screens.Update.route) }
            )
        }

        composable(route = Screens.Add.route){
            AddTaskScreen { navController.popBackStack() }
        }

        composable(route = Screens.Update.route){
            UpdateTaskScreen { navController.popBackStack() }
        }
    }
}