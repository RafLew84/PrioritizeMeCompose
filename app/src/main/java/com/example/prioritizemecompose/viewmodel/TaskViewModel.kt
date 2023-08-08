package com.example.prioritizemecompose.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prioritizemecompose.data.db.Task
import com.example.prioritizemecompose.data.rpeository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : ViewModel() {
    private val repository = TaskRepository(application)

    fun addAll(tasks: List<Task>){
        viewModelScope.launch {
            repository.insertAllTasks(tasks)
        }
    }
}