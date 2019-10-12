package io.keepcoding.tareas.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(MyTypeConverters::class)
@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

}