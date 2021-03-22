package com.todo.williamlavit.titouandesouza.tasklist

import java.io.Serializable

data class Task(val id: String, val title: String, val description: String = "C'est la description"): Serializable
