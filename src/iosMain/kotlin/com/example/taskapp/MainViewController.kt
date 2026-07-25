package com.example.taskapp

import androidx.compose.ui.window.ComposeUIViewController
import com.example.taskapp.ui.TaskViewModel

// Nota: En iOS, la inicialización de Room requiere un Database Driver específico de la plataforma.
// Esta función es el punto de entrada para el framework de Compose en iOS.
import com.example.taskapp.di.appModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun MainViewController() = ComposeUIViewController {
    startKoin {
        modules(appModule, module { /* Aquí iría el driver de Room para iOS */ })
    }
    
    // App(org.koin.compose.koinInject())
}
