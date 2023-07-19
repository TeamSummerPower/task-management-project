package com.example.tasksmspring.controllers;

import com.example.tasksmspring.users.User;
import com.example.tasksmspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/user/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        boolean isUsernameTaken = userService.getUserByUserName(user).isPresent();
        if (!isUsernameTaken) {
            User newUser = userService.createUser(user);
            return ResponseEntity.ok(String.valueOf(newUser.getId()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        Optional<User> optionalUser = userService.getUserByUserName(user);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (existingUser.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok(String.valueOf(existingUser.getId()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PutMapping("/user/updatePassword/{userId}")
    public ResponseEntity<String> updateUserPassword(@PathVariable Long userId, @RequestBody String newPassword) {
        Optional<User> isExists = userService.getUserById(userId);
        if (isExists.isPresent()) {
            User user = isExists.get();
            user.setPassword(newPassword);
            userService.updateUser(user);
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.ok("No such user found");
        }
    }
}
