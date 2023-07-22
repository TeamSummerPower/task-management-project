package com.example.tasksmspring.controllers;

import com.example.tasksmspring.tasks.Task;
import com.example.tasksmspring.services.TaskService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;
    private static final Gson gson = new Gson();
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping("/createTask")
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        Task newTask = taskService.createTask(task);
        if (newTask != null) {
            return ResponseEntity.ok(gson.toJson(newTask.getId()));
        } else {
            return ResponseEntity.ok(gson.toJson("Task couldn't be created!"));
        }
    }
    @DeleteMapping("/deleteTasks")
    public ResponseEntity<String> deleteTasks(@RequestBody List<Long> taskIds) {
        taskService.deleteTask(taskIds);
        return ResponseEntity.ok(gson.toJson("Tasks were deleted successfully"));
    }
    @PutMapping("/updateTasks")
    public ResponseEntity<String> updateTask(@RequestBody List<Long> taskIds) {
        List<Task> taskToBeUpdated = new ArrayList<>();
        for (var tId : taskIds) {
            Optional<Task> taskOptional = taskService.getTaskById(tId);
            taskOptional.ifPresent(taskToBeUpdated::add);
        }

        taskService.updateTask(taskToBeUpdated);

        return ResponseEntity.ok(gson.toJson("Task were updated successfully!"));
    }
}
