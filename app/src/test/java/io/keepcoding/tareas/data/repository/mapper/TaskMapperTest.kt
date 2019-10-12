package io.keepcoding.tareas.data.repository.mapper

import io.keepcoding.tareas.data.repository.local.TaskEntity
import org.junit.Assert
import org.junit.Test
import org.threeten.bp.Instant

class TaskMapperTest {

    val mapper = TaskMapper()

    @Test
    fun `When a task entity is mapped then a task should be returned`() {
        val now = Instant.now()

        val taskEntity = TaskEntity(
            1,
            "Esto es una prueba",
            now,
            false,
            true
        )

        val task = mapper.map(taskEntity)

        Assert.assertEquals(taskEntity.id, task.id)
        Assert.assertEquals(taskEntity.content, task.content)
        Assert.assertEquals(taskEntity.createdAt, task.createdAt)
        Assert.assertEquals(taskEntity.isHighPriority, task.isHighPriority)
        Assert.assertEquals(taskEntity.isFinished, task.isFinished)
    }

}