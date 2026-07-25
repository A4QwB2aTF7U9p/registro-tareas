package com.example.taskapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.taskapp.data.AppDatabase
import com.example.taskapp.ui.TaskViewModel

import com.example.taskapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "task-database").build()
        
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule, module { single { db.taskDao() } })
        }

        setContent {
            App(org.koin.compose.koinInject())
        }
    }
}
