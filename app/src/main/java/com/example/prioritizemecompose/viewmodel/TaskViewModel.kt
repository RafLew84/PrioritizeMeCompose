package com.example.prioritizemecompose.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prioritizemecompose.data.db.Priority
import com.example.prioritizemecompose.data.db.Task
import com.example.prioritizemecompose.data.dummydata.DataProvider
import com.example.prioritizemecompose.data.rpeository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : ViewModel() {

    private val repository = TaskRepository(application)

    private val _tasksState = MutableStateFlow<List<Task>>(emptyList())
    val tasksState: StateFlow<List<Task>>
        get() = _tasksState

    val priorities = Priority.values().toList()

    init {
        reinitializeDatabaseWithDummyData()
        getAll()
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

    private fun getAll(){
        viewModelScope.launch {
            repository.getTasks().collect{tasks ->
                _tasksState.value = tasks
            }
        }
    }

    fun filterByTitle(title: String){
        viewModelScope.launch {
            repository.getFilteredTasksByTitle("%$title%").collect {filteredList ->
                _tasksState.value = filteredList
            }
        }
    }

    fun filteredByPriority(priority: String){
        viewModelScope.launch {
            repository.getFilteredTasksByPriority(priority).collect { filteredList ->
                _tasksState.value = filteredList
            }
        }
    }
}