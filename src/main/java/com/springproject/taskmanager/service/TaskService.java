package com.springproject.taskmanager.service;

import com.springproject.taskmanager.model.Task;
import com.springproject.taskmanager.payload.TaskDto;
import com.springproject.taskmanager.repository.TaskRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if(existingTask != null) {
            existingTask.setTitle(taskDto.getTitle());
            existingTask.setDescription(taskDto.getDescription());
            existingTask.setCompleted(taskDto.isCompleted());
            return taskRepository.save(existingTask);
        }
        return null;
    }


    public boolean deleteTask(Long id) {
        try{
            taskRepository.deleteById(id);
            return true;
        }
        catch(EmptyResultDataAccessException ex){
            return false;
        }
    }
}
