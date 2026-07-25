package com.example.taskapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskapp.domain.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("Media") }
    var editingTask by remember { mutableStateOf<Task?>(null) }
    val tasks by viewModel.tasks.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mis Tareas") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            OutlinedTextField(
                value = title, 
                onValueChange = { title = it }, 
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description, 
                onValueChange = { description = it }, 
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Alta", "Media", "Baja").forEach { p ->
                    FilterChip(
                        selected = priority == p,
                        onClick = { priority = p },
                        label = { Text(p) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { 
                    if (editingTask == null) {
                        viewModel.addTask(title, description, "Hoy", priority) 
                    } else {
                        viewModel.updateTask(editingTask!!.copy(title = title, description = description, priority = priority))
                        editingTask = null
                    }
                    title = ""
                    description = ""
                    priority = "Media"
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (editingTask == null) "Añadir Tarea" else "Actualizar Tarea")
            }

            Spacer(modifier = Modifier.height(24.dp))
            
            Text("Tareas Pendientes", style = MaterialTheme.typography.headlineSmall)
            
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 8.dp)) {
                items(tasks) { task ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth().clickable { 
                            editingTask = task
                            title = task.title
                            description = task.description
                            priority = task.priority
                        }
                    ) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = task.title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                                Text(text = "${task.description} | Prioridad: ${task.priority}", style = MaterialTheme.typography.bodyMedium)
                            }
                            IconButton(onClick = { viewModel.deleteTask(task) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Borrar")
                            }
                        }
                    }
                }
            }
        }
    }
}
