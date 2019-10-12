package io.keepcoding.tareas.presentation.tasks

import androidx.recyclerview.widget.DiffUtil
import io.keepcoding.tareas.domain.model.Task

class TaskDiffUtil : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
        oldItem == newItem
}