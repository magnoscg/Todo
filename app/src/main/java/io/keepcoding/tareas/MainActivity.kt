package io.keepcoding.tareas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import io.keepcoding.tareas.presentation.tasks.TasksFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpFragment(savedInstanceState)
        setUpToolbar()
    }

    private fun setUpFragment(savedInstanceState: Bundle?) {
        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, TasksFragment())
                .commit()
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar as Toolbar)
    }

}
