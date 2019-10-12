package io.keepcoding.tareas.presentation.task.add_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.keepcoding.tareas.presentation.task.TaskViewModel
import io.keepcoding.util.extensions.consume
import io.keepcoding.util.extensions.observe
import kotlinx.android.synthetic.main.add_task_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.threeten.bp.Instant


class AddTaskFragment : Fragment() {

    val taskViewModel: TaskViewModel by viewModel()
   /**** var dateSelected: Instant = Instant.now() ****/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(io.keepcoding.tareas.R.layout.add_task_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindEvents()
        bindActions()
    }

    private fun bindActions() {
        addButton.setOnClickListener {
            val taskContent = taskContent.text.toString()
            val taskPriority = taskPrioritySwitch.isChecked
            val taskDate = Instant.now()

            taskViewModel.save(taskContent, taskPriority, taskDate)
        }
    }

    private fun bindEvents() {
        with (taskViewModel) {
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