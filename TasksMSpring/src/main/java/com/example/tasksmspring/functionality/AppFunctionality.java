package com.example.tasksmspring.functionality;

import com.example.tasksmspring.database.DataBaseManagement;
import com.example.tasksmspring.tasks.Task;
import com.example.tasksmspring.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("app")
public class AppFunctionality {
    private final DataBaseManagement dataBaseManagement;
    @Autowired
    public AppFunctionality(DataBaseManagement dataBaseManagement) {
        this.dataBaseManagement = dataBaseManagement;
    }
    @PostMapping("/user/register")
    public ResponseEntity<Long> registerUser(@RequestBody User user) {
                boolean isUsernameTaken = dataBaseManagement.getUserByUserName(user).isPresent();
                if (!isUsernameTaken) {
                    User newUser = dataBaseManagement.createUser(user);
                    return ResponseEntity.ok(newUser.getId());
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/user/login")
    public ResponseEntity<Long> loginUser(@RequestBody User user) {
        Optional<User> optionalUser = dataBaseManagement.getUserByUserName(user);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (existingUser.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok(existingUser.getId());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/user/createTask")
    public ResponseEntity<Long> createTask(@RequestBody Task task) {
        Task newTask = dataBaseManagement.createTask(task);
        if (newTask != null) {
            return ResponseEntity.ok(newTask.getId());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/user/deleteTasks")
    public ResponseEntity<String> deleteTasks(@RequestBody List<Long> taskIds) {
        dataBaseManagement.deleteTask(taskIds);
        return ResponseEntity.ok("Tasks were deleted successfully");
    }
    @PutMapping("/user/updateTask/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        Optional<Task> taskOptional = dataBaseManagement.getTaskById(taskId);


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

            Task savedTask = dataBaseManagement.updateTask(existingTask);

            if (savedTask != null) {
                return ResponseEntity.ok("Task updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/user/updatePassword/{userId}")
    public ResponseEntity<String> updateUserPassword(@PathVariable Long userId, @RequestBody String newPassword) {
        Optional<User> isExists = dataBaseManagement.getUserById(userId);
        if (isExists.isPresent()) {
            User user = isExists.get();
            user.setPassword(newPassword);
            dataBaseManagement.updateUser(user);
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.ok("No such user found");
        }
    }
}
