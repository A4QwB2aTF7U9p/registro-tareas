package com.example.taskapp

import com.example.taskapp.data.TaskDao
import com.example.taskapp.domain.Task
import com.example.taskapp.ui.TaskViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class TaskViewModelTest {

    @Test
    fun `addTask adds task to database`() = runTest {
        val mockDao = mock<TaskDao>()
        whenever(mockDao.getAllTasks()).thenReturn(flowOf(emptyList()))
        
        val viewModel = TaskViewModel(mockDao)
        val task = Task(title = "Test", description = "Desc", dueDate = "Hoy")
        
        viewModel.addTask(task.title, task.description, task.dueDate)
        
        verify(mockDao).insert(org.mockito.kotlin.any())
    }
}
