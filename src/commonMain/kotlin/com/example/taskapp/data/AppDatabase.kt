package com.example.taskapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskapp.domain.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
