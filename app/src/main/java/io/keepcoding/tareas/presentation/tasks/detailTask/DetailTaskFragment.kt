package io.keepcoding.tareas.presentation.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.keepcoding.tareas.R
import io.keepcoding.tareas.data.repository.local.MyTypeConverters
import io.keepcoding.tareas.domain.model.Task
import io.keepcoding.util.extensions.observe
import kotlinx.android.synthetic.main.fragment_detail_task.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class DetailTaskFragment : Fragment() {

    private val tasksViewModel: TasksViewModel by viewModel()
    var task: Task? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_task, container, false)
    }

    /*override fun onResume() {
        super.onResume()
        taskViewModel.loadTaskData(task?.id!!)
    }
*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.let {
            it.getLong("id", 0)
        }
        tasksViewModel.loadTasks(id!!)

        bindState()
        bindActions()
    }


    private fun bindActions() {

        taskViewModel.tasksState.value?.let{ currentTask ->

            updateButton.setOnClickListener {
                val taskContent = taskContent.text.toString()
                val taskPriority = taskPrioritySwitch.isChecked
                val taskFinished = taskFinishedCheck.isChecked

                taskViewModel.update(task!!.id, taskContent, taskPriority, taskFinished, currentTask.createdAt)
            }
        }
    }

    private fun loadDetails(task: Task?) {

        task?.let { currentTask ->

            taskContent.setText(currentTask.content)
            taskPrioritySwitch.isChecked = currentTask.isHighPriority
            taskFinishedCheck.isChecked = currentTask.isFinished

            // Fill Date
            val dateConverted = MyTypeConverters()
            val date = Date(dateConverted.fromLocalDateTime(currentTask.createdAt))
            val pretty = PrettyTime(Locale.forLanguageTag("en"))  // Library to set up the date counting the time passed since the creation (f.i.: hace 1 día)
            taskDate.text = pretty.format(date)


        }

    }


    private fun bindState() {
        with (taskViewModel) {
            observe(tasksState) {
                loadDetails(it)
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