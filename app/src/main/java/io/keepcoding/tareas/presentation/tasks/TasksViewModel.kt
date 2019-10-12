package io.keepcoding.tareas.presentation.tasks

import androidx.lifecycle.MutableLiveData
import io.keepcoding.tareas.domain.TaskRepository
import io.keepcoding.tareas.domain.model.Task
import io.keepcoding.tareas.presentation.BaseViewModel
import io.keepcoding.util.DispatcherFactory
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import io.keepcoding.util.Event



class TasksViewModel(
    private val taskRepository: TaskRepository,
    private val dispatcherFactory: DispatcherFactory

): BaseViewModel(dispatcherFactory) {

    val tasksState = MutableLiveData<List<Task>>()
    val isLoadingState = MutableLiveData<Boolean>()
    val closeAction = MutableLiveData<Event<Unit>>()

    //private fun validateContent(content: String): Boolean = content.isNotEmpty()

    fun loadTasks() {
        launch {
            showLoading(true)

            val result = withContext(dispatcherFactory.getIO()) { taskRepository.getAll() }
            tasksState.value = result

            showLoading(false)
        }
    }

    fun toggleFinished(task: Task) {
        val newTask = task.copy(isFinished = !task.isFinished)

        launch(dispatcherFactory.getIO()) {
            taskRepository.updateTask(newTask)
        }
    }

    fun removeSelectedTask(task: Task) {

        launch(dispatcherFactory.getIO()) {
            taskRepository.removeTask(task)
            loadTasks()
        }

    }

    private fun showLoading(isLoading: Boolean) {
        isLoadingState.value = isLoading
    }
}