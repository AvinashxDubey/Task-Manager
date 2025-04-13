package com.springproject.taskmanager.controller;

import com.springproject.taskmanager.model.Task;
import com.springproject.taskmanager.payload.ApiResponse;
import com.springproject.taskmanager.payload.TaskDto;
import com.springproject.taskmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//    fetches all tasks
    @GetMapping
    public ResponseEntity<ApiResponse<List<Task>>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        ApiResponse<List<Task>> response = new ApiResponse<>(
                true,
                tasks.isEmpty()? "No tasks found." : "Tasks retrieved successfully",
                tasks
        );
        return ResponseEntity.ok(response);
    }


//    adds a new task
    @PostMapping
    public  ResponseEntity<ApiResponse<Task>> createTask(@RequestBody  Task task) {
        Task createdTask = taskService.createTask(task);
        ApiResponse<Task> response = new ApiResponse<>(true, "Task created successfully", createdTask);
        return ResponseEntity.ok(response);
    }

//    deletes a task
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> deleteTask(@PathVariable Long id) {
        boolean deleteTask = taskService.deleteTask(id);
        if(deleteTask){
            ApiResponse<Task> response = new ApiResponse<> (true, "Task deleted successfully", null);
            return ResponseEntity.ok(response);
        }
        else{
            ApiResponse<Task> response = new ApiResponse<> (false, "Task not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> updateTask(@PathVariable long id,@RequestBody TaskDto taskDto) {
        Task updatedTask = taskService.updateTask(id, taskDto);
        if(updatedTask != null){
            ApiResponse<Task> response = new ApiResponse<> (true, "Task updated successfully", updatedTask);
            return ResponseEntity.ok(response);
        }
        else{
            ApiResponse<Task> response = new ApiResponse<> (false, "Task not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


}
