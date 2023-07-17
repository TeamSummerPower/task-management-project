package com.example.tasksmspring.controllers;

import com.example.tasksmspring.tasks.Task;
import com.example.tasksmspring.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping("/user/createTask")
    public ResponseEntity<Long> createTask(@RequestBody Task task) {
        Task newTask = taskService.createTask(task);
        if (newTask != null) {
            return ResponseEntity.ok(newTask.getId());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/user/deleteTasks")
    public ResponseEntity<String> deleteTasks(@RequestBody List<Long> taskIds) {
        taskService.deleteTask(taskIds);
        return ResponseEntity.ok("Tasks were deleted successfully");
    }
    @PutMapping("/user/updateTask/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        Optional<Task> taskOptional = taskService.getTaskById(taskId);

        if (taskOptional.isPresent()) {
            Task existingTask = taskOptional.get();

            if (updatedTask.getTitle() != null) {
                existingTask.setTitle(updatedTask.getTitle());
            }
            if (updatedTask.getDescription() != null) {
                existingTask.setDescription(updatedTask.getDescription());
            }
            if (updatedTask.getPriority() != null) {
                existingTask.setPriority(updatedTask.getPriority());
            }
            if (updatedTask.isRec()) {
                existingTask.setRec(updatedTask.isRec());
            }

            Task savedTask = taskService.updateTask(existingTask);

            if (savedTask != null) {
                return ResponseEntity.ok("Task updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
