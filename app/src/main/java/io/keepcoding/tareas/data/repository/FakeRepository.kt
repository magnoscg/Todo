package io.keepcoding.tareas.data.repository

import io.keepcoding.tareas.domain.TaskRepository
import io.keepcoding.tareas.domain.model.Task
import kotlinx.coroutines.runBlocking
import org.threeten.bp.Instant

class FakeRepository : TaskRepository {

    val tasks: MutableMap<Long, Task> =
        mutableMapOf(
            1L to Task(1, "Este es el primer task 🔥", Instant.now(), false, false),
            2L to Task(
                2,
                "Otro mensaje que la verdad no me importa pero este es algo más largo a ver cómo se comporta",
                Instant.now(),
                true,
                true
            ),
            3L to Task(3, "Los emojis molan 🔥😊❤️😛🎂💃", Instant.now(), false, true)
        )

    override suspend fun getAll(): List<Task> = runBlocking {
        tasks.values.toList()
    }

    override suspend fun getTaskById(id: Long): Task = runBlocking {
        tasks[id]!!
    }

    override suspend fun addTask(task: Task) {
        tasks[task.id] = task
    }

    override suspend fun updateTask(task: Task) {
        tasks[task.id] = task
    }

}