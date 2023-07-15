package com.example.tasksmspring.functionality;

import com.example.tasksmspring.users.LoginDTO;
import com.example.tasksmspring.users.RegistrationDTO;
import com.example.tasksmspring.users.Role;
import com.example.tasksmspring.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppFunctionality {

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        User newUser = createUser(registrationDTO);
        // register in DB
        return ResponseEntity.ok("User has been registered successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) {
        // retrieve from the DB
        boolean bool = true;
        if (bool) {
            return ResponseEntity.ok("Login has been successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

    }
    private User createUser(RegistrationDTO registrationDTO) {
        String name = registrationDTO.getName();
        String email = registrationDTO.getEmail();
        String password = registrationDTO.getPassword();
        return new User(name, email, password, Role.USER);
    }
}
