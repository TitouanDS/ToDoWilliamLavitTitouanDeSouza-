package com.todo.williamlavit.titouandesouza.tasklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.todo.williamlavit.titouandesouza.R

class TaskListAdapter(private val taskList: List<Task>) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {
    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.task_title)
        fun bind(taskTitle: Task) {
            textView.text = taskTitle.toString()
        }
    }


    var onDeleteTask: ((Task) -> Unit)? = null



    var TaskListAdapter.onDeleteTask: Function1<Task, *>
        get() = { Task ->
            Task =  null
        }
        set(value) = null



    onDeleteTask?.invoke(task)
}

