package io.keepcoding.tareas.data.repository.mapper

import io.keepcoding.tareas.data.repository.local.TaskEntity
import io.keepcoding.tareas.domain.model.Task

class TaskMapper : Mapper<TaskEntity, Task> {

    override fun map(input: TaskEntity): Task =
        with(input) {
            Task(
                id,
                content,
                createdAt,
                isHighPriority,
                isFinished
            )
        }

}