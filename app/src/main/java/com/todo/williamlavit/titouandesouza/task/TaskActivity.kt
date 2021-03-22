package com.todo.williamlavit.titouandesouza.task

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.todo.williamlavit.titouandesouza.R
import com.todo.williamlavit.titouandesouza.tasklist.Task
import java.util.*

class TaskActivity : AppCompatActivity() {

    companion object {
        const val TASK_KEY = "TASK"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)


        val buttonConfirm = findViewById<Button>(R.id.buttonSubmit)
        val editTitle = findViewById<EditText>(R.id.editTextTitle)
        val editDescription = findViewById<EditText>(R.id.editTextDescription)

        buttonConfirm.setOnClickListener {
            val taskAdd = Task(id = UUID.randomUUID().toString(), title = editTitle.text.toString(), description = editDescription.text.toString())
            intent.putExtra(TASK_KEY, taskAdd)
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}