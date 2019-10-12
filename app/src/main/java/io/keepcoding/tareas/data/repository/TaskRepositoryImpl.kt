package io.keepcoding.tareas.data.repository

import io.keepcoding.tareas.data.repository.local.TaskDao
import io.keepcoding.tareas.data.repository.local.TaskEntity
import io.keepcoding.tareas.data.repository.mapper.TaskEntityMapper
import io.keepcoding.tareas.data.repository.mapper.TaskMapper
import io.keepcoding.tareas.domain.TaskRepository
import io.keepcoding.tareas.domain.model.Task

class TaskRepositoryImpl(
    private val taskDao: TaskDao,
    private val taskMapper: TaskMapper,
    private val taskEntityMapper: TaskEntityMapper
) : TaskRepository {

    override suspend fun getAll(): List<Task> =
        taskDao.getAll().map { taskMapper.map(it) }

    override suspend fun getTaskById(id: Long): Task =
        taskMapper.map(taskDao.getTaskById(id))

    override suspend fun addTask(task: Task) {
        taskDao.insert(taskEntityMapper.map(task))
    }

    override suspend fun updateTask(task: Task) {
        taskDao.update(taskEntityMapper.map(task))
    }
}