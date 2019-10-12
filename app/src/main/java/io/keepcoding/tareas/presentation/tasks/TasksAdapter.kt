package io.keepcoding.tareas.presentation.tasks

import android.animation.ValueAnimator
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.keepcoding.tareas.R
import io.keepcoding.tareas.data.repository.local.MyTypeConverters
import io.keepcoding.tareas.domain.model.Task
import kotlinx.android.synthetic.main.item_task.view.*
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class TasksAdapter(
    private val onActionSelected: (task: Task, action: String) -> Unit
) : ListAdapter<Task, TasksAdapter.TaskViewHolder>(TaskDiffUtil()) {


    lateinit var taskClickListener: OnTaskClickListener
    lateinit var editClickListener: OnEditClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) = taskClickListener.onTaskClick(itemView, getItem(adapterPosition))

        fun bind(task: Task) {
            with (itemView) {
                cardContentText.text = task.content

                if (task.isHighPriority) {
                    cardPriority.text = this.resources.getString(R.string.label_priority)
                    cardPriority.setTextAppearance(R.style.TaskCard_Content_SubTextLight)
                }
                else {
                    cardPriority.text = ""
                }

                // Fill Date
                val dateConverted = MyTypeConverters()
                val date = Date(dateConverted.fromLocalDateTime(task.createdAt))
                val pretty = PrettyTime(Locale.forLanguageTag("en"))
                cardDate.text = pretty.format(date)


                taskFinishedCheck.isChecked = task.isFinished

                if (task.isFinished) {
                    applyStrikeThrough(cardContentText, task.content)
                } else {
                    removeStrikeThrough(cardContentText, task.content)
                }

                taskFinishedCheck.setOnClickListener {
                    onActionSelected(task, R.string.actionToggle.toString())

                    if (taskFinishedCheck.isChecked) {
                        applyStrikeThrough(cardContentText, task.content, animate = true)
                    } else {
                        removeStrikeThrough(cardContentText, task.content, animate = true)
                    }
                }

                btnDelete.setOnClickListener {
                   //TODO: remove task
                    onActionSelected(task, R.string.actionDelete.toString())

                }


                btnEdit.setOnClickListener{
                    editClickListener.onEditClick(it, task)
                }

            }
        }

        private fun applyStrikeThrough(view: TextView, content: String, animate: Boolean = false) {
            val span = SpannableString(content)
            val spanStrike = StrikethroughSpan()

            if (animate) {
                ValueAnimator.ofInt(content.length).apply {
                    duration = 300
                    interpolator = FastOutSlowInInterpolator()
                    addUpdateListener {
                        span.setSpan(spanStrike, 0, it.animatedValue as Int, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                        view.text = span
                    }
                }.start()
            } else {
                span.setSpan(spanStrike, 0, content.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                view.text = span
            }
        }

        private fun removeStrikeThrough(view: TextView, content: String, animate: Boolean = false) {
            val span = SpannableString(content)
            val spanStrike = StrikethroughSpan()

            if(animate) {
                ValueAnimator.ofInt(content.length, 0).apply {
                    duration = 300
                    interpolator = FastOutSlowInInterpolator()
                    addUpdateListener {
                        span.setSpan(spanStrike, 0, it.animatedValue as Int, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                        view.text = span
                    }
                }.start()
            } else {
                view.text = content
            }
        }

    }

    interface OnTaskClickListener {
        fun onTaskClick(view: View, task: Task)
    }

    fun setOnTaskClickListener(itemClickListener: OnTaskClickListener) {
        this.taskClickListener = itemClickListener
    }

    interface OnEditClickListener {
        fun onEditClick(view: View, task: Task)
    }

    fun setOnEditClickListener(editClickListener: OnEditClickListener) {
        this.editClickListener = editClickListener
    }
}
