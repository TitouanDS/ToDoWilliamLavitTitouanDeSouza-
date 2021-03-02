package com.todo.williamlavit.titouandesouza.tasklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.todo.williamlavit.titouandesouza.R
import java.util.*

class TaskListAdapter(private val taskList: List<Task>) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.task_title)
        private val deleteButton = itemView.findViewById<ImageButton>(R.id.imageButton2)

        fun bind(task: Task) {
            textView.text = task.toString()

            deleteButton.setOnClickListener {
                onDeleteTask?.invoke(task)
            }
        }
    }

    var onDeleteTask: ((Task) -> Unit)? = null
}

