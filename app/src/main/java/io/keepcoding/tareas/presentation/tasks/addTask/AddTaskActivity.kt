package io.keepcoding.tareas.presentation.tasks.addTask

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import io.keepcoding.tareas.R
import kotlinx.android.synthetic.main.activity_main.*

class AddTaskActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        setUpToolbar()
        setUpFragment(savedInstanceState)
    }
    private fun setUpToolbar() {
        setSupportActionBar(toolbar as Toolbar)
        setTitle(R.string.add_todo)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }
    private fun setUpFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, AddTaskFragment())
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