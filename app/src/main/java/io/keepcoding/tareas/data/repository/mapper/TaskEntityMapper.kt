package io.keepcoding.tareas.data.repository.mapper

import io.keepcoding.tareas.data.repository.local.TaskEntity
import io.keepcoding.tareas.domain.model.Task

class TaskEntityMapper : Mapper<Task, TaskEntity> {

    override fun map(input: Task): TaskEntity =
            with (input) {
                TaskEntity(
                    id,
                    content,
                    createdAt,
                    isHighPriority,
                    isFinished
                )
            }

}