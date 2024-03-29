package network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.todo.williamlavit.titouandesouza.tasklist.Task

class TasksRepository {
    private val tasksWebService = Api.tasksWebService

    private val _taskList = MutableLiveData<List<Task>>()

    public val taskList: LiveData<List<Task>> = _taskList

    private val webService = Api.tasksWebService
    suspend fun loadTasks(): List<Task>? {
        val response = webService.getTasks()
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun create(task:Task){
        // Call HTTP (opération longue):
        val createResponse = tasksWebService.createTask(task)

        val editableList = _taskList.value.orEmpty().toMutableList()
        editableList.add(createResponse.body()!!)
        _taskList.value = editableList
    }

    suspend fun update(task:Task){
        val updateResponse = tasksWebService.createTask(task)
        val updatedTask = updateResponse.body()!! //
        val editableList = _taskList.value.orEmpty().toMutableList()
        val position = editableList.indexOfFirst { task.id == it.id }
        editableList[position] = updatedTask
        _taskList.value = editableList
    }

    suspend fun delete(task: Task){
        val deleteResponse = tasksWebService.deleteTask(task.id)
        val editableList = _taskList.value.orEmpty().toMutableList()
        editableList.remove(task)
        _taskList.value = editableList
    }
    suspend fun refresh() {
        val tasksResponse = tasksWebService.getTasks()
        if (tasksResponse.isSuccessful) {
            val fetchedTasks = tasksResponse.body()
            _taskList.value = fetchedTasks
        }
    }
}
