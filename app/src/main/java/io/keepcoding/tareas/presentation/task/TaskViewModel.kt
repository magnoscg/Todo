package io.keepcoding.tareas.presentation.task

import androidx.lifecycle.MutableLiveData
import io.keepcoding.tareas.domain.TaskRepository
import io.keepcoding.tareas.domain.model.Task
import io.keepcoding.tareas.presentation.BaseViewModel
import io.keepcoding.util.DispatcherFactory
import io.keepcoding.util.Event
import io.keepcoding.util.extensions.call
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.Instant

class TaskViewModel(
private val taskRepository: TaskRepository,
private val dispatcherFactory: DispatcherFactory
) : BaseViewModel(dispatcherFactory) {

    val taskState = MutableLiveData<Task>()
    val closeAction = MutableLiveData<Event<Unit>>()

    fun save(content: String, priority: Boolean, date: Instant) {
        if (!validateContent(content)) {
            return
        }

        launch {
            withContext(dispatcherFactory.getIO()) {
                taskRepository.addTask(Task(0, content, date, priority, false))
            }
            closeAction.call()
        }
    }

    fun loadTaskData(taskId: Long) {

        launch {

            taskState.value = withContext(dispatcherFactory.getIO()) {
                taskRepository.getTaskById(id = taskId)
            }

        }

    }

    fun update(task: Task, content: String, priority: Boolean, finished: Boolean, createdAt: Instant) {
        if (!validateContent(content)) {
            return
        }

        launch {
            withContext(dispatcherFactory.getIO()) {
                val updatedData = task.copy(content = content, isHighPriority = priority, isFinished = finished, createdAt = createdAt)
                taskRepository.updateTask(updatedData)
            }
            closeAction.call()
        }
    }

    private fun validateContent(content: String): Boolean =
        content.isNotEmpty()






}