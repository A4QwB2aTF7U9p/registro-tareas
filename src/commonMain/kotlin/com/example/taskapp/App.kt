package com.example.taskapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.taskapp.ui.TaskScreen
import com.example.taskapp.ui.TaskViewModel

// Nota: En una implementación real, aquí se inyectaría el DAO
// proveniente de la base de datos configurada para cada plataforma.
@Composable
fun App(viewModel: TaskViewModel) {
    MaterialTheme {
        TaskScreen(viewModel = viewModel)
    }
}
