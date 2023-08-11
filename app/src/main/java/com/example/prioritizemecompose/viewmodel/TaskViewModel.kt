package com.example.prioritizemecompose.viewmodel

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prioritizemecompose.data.db.Priority
import com.example.prioritizemecompose.data.db.Task
import com.example.prioritizemecompose.data.dummydata.DataProvider
import com.example.prioritizemecompose.data.rpeository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : ViewModel() {

    private val repository = TaskRepository(application)

    val tasksState: StateFlow<List<Task>> = repository.getTasks().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    init {
        reinitializeDatabaseWithDummyData()
    }

    private fun reinitializeDatabaseWithDummyData(){
        deleteAll()
        addAll(DataProvider.tasks)
    }

    private fun addAll(tasks: List<Task>){
        viewModelScope.launch {
            repository.insertAllTasks(tasks)
        }
    }

    private fun deleteAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch{
            repository.deleteTask(task)
        }
    }

    fun getTaskColor(task: Task): Color{
        return when(task.priority) {
            Priority.WYSOKI -> Color.Red
            Priority.NISKI -> Color.Blue
            Priority.NORMALNY -> Color.Green
            else -> Color(128, 203, 196)
        }
    }

    fun increasePriority(task: Task) {
        val nextPriority = when (task.priority) {
            Priority.WYSOKI -> Priority.WYSOKI
            Priority.NORMALNY -> Priority.WYSOKI
            Priority.NISKI -> Priority.NORMALNY
            Priority.WYKONANY -> Priority.WYKONANY
        }

        updateTask(task.copy(priority = nextPriority))
    }

    fun decreasePriority(task: Task) {
        val nextPriority = when (task.priority) {
            Priority.WYSOKI -> Priority.NORMALNY
            Priority.NORMALNY -> Priority.NISKI
            Priority.NISKI -> Priority.NISKI
            Priority.WYKONANY -> Priority.WYKONANY
        }

        updateTask(task.copy(priority = nextPriority))
    }

    fun donePriority(task: Task) {
        val nextPriority = when (task.priority) {
            Priority.WYSOKI -> Priority.WYKONANY
            Priority.NORMALNY -> Priority.WYKONANY
            Priority.NISKI -> Priority.WYKONANY
            Priority.WYKONANY -> Priority.WYKONANY
        }

        updateTask(task.copy(priority = nextPriority, isDone = true))
    }
}