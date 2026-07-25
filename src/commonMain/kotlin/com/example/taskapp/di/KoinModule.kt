package com.example.taskapp.di

import com.example.taskapp.ui.TaskViewModel
import org.koin.dsl.module

val appModule = module {
    // Aquí se definirían las dependencias.
    // El DAO debe ser proveído por la plataforma.
    single { TaskViewModel(get()) }
}
