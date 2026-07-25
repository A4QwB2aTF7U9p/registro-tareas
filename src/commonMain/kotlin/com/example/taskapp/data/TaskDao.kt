package com.example.taskapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taskapp.domain.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Insert
    suspend fun insert(task: Task)

    @androidx.room.Delete
    suspend fun delete(task: Task)

    @androidx.room.Update
    suspend fun update(task: Task)
}
