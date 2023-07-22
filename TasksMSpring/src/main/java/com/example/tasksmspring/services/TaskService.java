package com.example.tasksmspring.services;

import com.example.tasksmspring.tasks.Task;
import com.example.tasksmspring.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public Task createTask (Task task) {
        return taskRepository.save(task);
    }
    public void deleteTask(List<Long> taskIds) {
        taskRepository.deleteAllById(taskIds);
    }
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    public List<Task> updateTask(List<Task> tasks) {
        return taskRepository.saveAll(tasks);
    }
}
