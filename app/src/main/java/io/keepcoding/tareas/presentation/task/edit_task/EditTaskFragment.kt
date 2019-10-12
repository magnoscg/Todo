package io.keepcoding.tareas.presentation.task.detail_task


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.keepcoding.tareas.R
import io.keepcoding.tareas.data.repository.local.MyTypeConverters
import io.keepcoding.tareas.domain.model.Task
import io.keepcoding.tareas.presentation.task.TaskViewModel
import io.keepcoding.util.extensions.consume
import io.keepcoding.util.extensions.observe
import kotlinx.android.synthetic.main.fragment_edit_task.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.ocpsoft.prettytime.PrettyTime
import org.threeten.bp.Instant
import java.util.*


class EditTaskFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModel()
    var task: Task? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_task, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.let {
            it.getLong("id", 0)
        }
        taskViewModel.loadTaskData(id!!)

        bindState()
    }


    private fun bindActions(task: Task) {

        modifyButton.setOnClickListener {
            val taskContent = taskContent.text.toString()
            val taskPriority = taskPrioritySwitch.isChecked
            val taskFinished = taskFinishedCheck.isChecked

            taskViewModel.update(task, taskContent, taskPriority, taskFinished, Instant.now())
        }
    }

    private fun loadDetails(task: Task?) {

        task?.let { currentTask ->

            taskContent.setText(currentTask.content)
            taskPrioritySwitch.isChecked = currentTask.isHighPriority
            taskFinishedCheck.isChecked = currentTask.isFinished
            val dateConverted = MyTypeConverters()
            val date = Date(dateConverted.fromLocalDateTime(currentTask.createdAt))
            val pretty = PrettyTime(Locale.forLanguageTag("en"))
            taskDate.text = pretty.format(date)


        }

    }


    private fun bindState() {
        with (taskViewModel) {
            observe(taskState) {
                loadDetails(it)
                bindActions(it)
            }
            observe(closeAction) {
                it.consume {
                    onClose()
                }
            }
        }
    }


    private fun onClose() {
        requireActivity().finish()
    }



}