package com.example.prioritizemecompose.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllTasks(tasks: List<Task>)

    @Query("SELECT * FROM task_table ORDER BY id ASC")
    fun getTasks(): Flow<List<Task>>

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM task_table WHERE title LIKE :title")
    fun getFilteredTasksByTitle(title: String): Flow<List<Task>>

    @Query("DELETE FROM task_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM task_table WHERE priority = :priority")
    fun getFilteredTasksByPriority(priority: String): Flow<List<Task>>
}