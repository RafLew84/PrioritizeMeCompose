package com.example.prioritizemecompose.ui

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prioritizemecompose.ui.screens.AddTaskScreen
import com.example.prioritizemecompose.ui.screens.TaskListScreen
import com.example.prioritizemecompose.ui.screens.UpdateTaskScreen
import com.example.prioritizemecompose.viewmodel.TaskViewModel
import com.example.prioritizemecompose.viewmodel.TaskViewModelFactory

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: TaskViewModel = viewModel(
        LocalViewModelStoreOwner.current!!,
        "TaskViewModel",
        TaskViewModelFactory(LocalContext.current.applicationContext as Application)
    )

    NavHost(navController = navController, startDestination = Screens.TaskList.route) {
        composable(route = Screens.TaskList.route){
            TaskListScreen (
                onAddScreen = {navController.navigate(Screens.Add.route) },
                onEditScreen = { navController.navigate(Screens.Update.route) },
                viewModel = viewModel
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