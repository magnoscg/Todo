package io.keepcoding.tareas.presentation.task.detail_task

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import io.keepcoding.tareas.R
import kotlinx.android.synthetic.main.activity_detail_task.*


class EditTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit_task)

        setUpToolbar()
        setUpFragmentDetail(savedInstanceState)

    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar as Toolbar)
        setTitle(R.string.edit_task)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    private fun setUpFragmentDetail(savedInstanceState: Bundle?) {
        if(savedInstanceState == null) {

            val fragment = EditTaskFragment()
            val taskId = intent.getLongExtra("id", 0)
            val args = Bundle()

            args.putLong("id", taskId)

            fragment.arguments = args

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.editContainer, fragment)
                .commit()

        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}