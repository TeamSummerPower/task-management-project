package com.example.tasksmspring.controllers;

import com.example.tasksmspring.tasks.Task;
import com.example.tasksmspring.services.TaskService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TaskController {
    private final TaskService taskService;
    private static final Gson gson = new Gson();
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping("/user/createTask")
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        Task newTask = taskService.createTask(task);
        if (newTask != null) {
            return ResponseEntity.ok(gson.toJson(newTask.getId()));
        } else {
            return ResponseEntity.ok(gson.toJson("Task couldn't be created!"));
        }
    }
    @DeleteMapping("/user/deleteTasks")
    public ResponseEntity<String> deleteTasks(@RequestBody List<Long> taskIds) {
        taskService.deleteTask(taskIds);
        return ResponseEntity.ok(gson.toJson("Tasks were deleted successfully"));
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
                return ResponseEntity.ok(gson.toJson("Task updated successfully"));
            } else {
                return ResponseEntity.ok(gson.toJson("Task couldn't be updated"));
            }
        } else {
            return ResponseEntity.ok(gson.toJson("Task couldn't be found!"));
        }
    }
}
