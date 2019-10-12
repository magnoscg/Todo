package io.keepcoding.tareas.data.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.Instant

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val content: String,
    val createdAt: Instant,
    val isHighPriority: Boolean,
    val isFinished: Boolean
)