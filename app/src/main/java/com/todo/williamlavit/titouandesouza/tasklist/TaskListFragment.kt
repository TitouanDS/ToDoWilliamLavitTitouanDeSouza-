package com.todo.williamlavit.titouandesouza.tasklist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.todo.williamlavit.titouandesouza.R
import com.todo.williamlavit.titouandesouza.task.TaskActivity
import java.util.*

class TaskListFragment : Fragment() {

    companion object {
        const val ADD_TASK_REQUEST_CODE = 666
    }

    private val taskList = mutableListOf(
            Task(id = "id_1", title = "Task 1", description = "description 1"),
            Task(id = "id_2", title = "Task 2"),
            Task(id = "id_3", title = "Task 3")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = TaskListAdapter(taskList)

        val floatingAddButton  = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        floatingAddButton.setOnClickListener {
//            val taskAdd = Task(id = UUID.randomUUID().toString(), title = "Task ${taskList.size + 1}")
//            taskList.add(taskAdd)
            val intent = Intent(activity, TaskActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST_CODE)
            adapter.notifyDataSetChanged()
        }

        recyclerView.adapter = adapter

        adapter.onDeleteTask = { task ->
            taskList.remove(task)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val task = data?.getSerializableExtra(TaskActivity.TASK_KEY) as? Task
        if (task != null) {
            taskList.add(task)
        }
    }



}