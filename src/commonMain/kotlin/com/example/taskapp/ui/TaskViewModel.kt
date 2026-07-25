package com.example.taskapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.data.TaskDao
import com.example.taskapp.domain.Task
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {
    val tasks = taskDao.getAllTasks().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addTask(title: String, description: String, dueDate: String, priority: String) {
        viewModelScope.launch {
            taskDao.insert(Task(title = title, description = description, dueDate = dueDate, priority = priority))
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.delete(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskDao.update(task)
        }
    }
}
