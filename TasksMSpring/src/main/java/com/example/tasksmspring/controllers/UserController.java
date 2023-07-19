package com.example.tasksmspring.controllers;

import com.example.tasksmspring.users.User;
import com.example.tasksmspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin
public class UserController {
    private final UserService userService;
    private static final Gson gson = new Gson();
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        boolean isUsernameTaken = userService.getUserByUserName(user).isPresent();
        if (!isUsernameTaken) {
            User newUser = userService.createUser(user);
            return ResponseEntity.ok(gson.toJson(newUser.getId()));
        } else {
            return ResponseEntity.ok(gson.toJson("This email is already taken!"));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        Optional<User> optionalUser = userService.getUserByUserName(user);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (existingUser.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok(gson.toJson(existingUser.getId()));
            } else {
                return ResponseEntity.ok(gson.toJson("Password is incorrect!"));
            }
        } else {
            return ResponseEntity.ok(gson.toJson("No such email found!"));
        }
    }
    @PutMapping("/updatePassword/{userId}")
    public ResponseEntity<String> updateUserPassword(@PathVariable Long userId, @RequestBody String newPassword) {
        Optional<User> isExists = userService.getUserById(userId);
        if (isExists.isPresent()) {
            User user = isExists.get();
            user.setPassword(newPassword);
            userService.updateUser(user);
            return ResponseEntity.ok(gson.toJson("Password updated successfully"));
        } else {
            return ResponseEntity.ok(gson.toJson("No such user found"));
        }
    }
}
