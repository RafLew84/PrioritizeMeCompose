package com.example.prioritizemecompose.data.rpeository

import android.app.Application
import com.example.prioritizemecompose.data.db.Task
import com.example.prioritizemecompose.data.db.TaskDatabase

class TaskRepository(private val application: Application) {
    private val db = TaskDatabase.getDatabase(application)
    private val dao = db.taskDao()

    fun getTasks() = dao.getTasks()
    fun getFilteredTasks(title: String) = dao.getFilteredTasks(title)

    suspend fun insertTask(task: Task) = dao.insertTask(task)
    suspend fun insertAllTasks(tasks: List<Task>) = dao.insertAllTasks(tasks)
    suspend fun deleteTask(task: Task) = dao.deleteTask(task)
    suspend fun updateTask(task: Task) = dao.updateTask(task)
}