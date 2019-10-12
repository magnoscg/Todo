package io.keepcoding.tareas.domain.model

import org.threeten.bp.Instant

data class Task(
    val id: Long,
    val content: String,
    val createdAt: Instant,
    val isHighPriority: Boolean,
    val isFinished: Boolean
)