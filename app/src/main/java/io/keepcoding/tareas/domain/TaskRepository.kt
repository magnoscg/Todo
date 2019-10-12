package io.keepcoding.tareas.domain

import io.keepcoding.tareas.domain.model.Task

interface TaskRepository {

    suspend fun getAll(): List<Task>

    suspend fun getTaskById(id: Long): Task

    suspend fun addTask(taskEntity: Task)

    suspend fun updateTask(taskEntity: Task)

    suspend fun removeTask(taskEntity: Task)
}