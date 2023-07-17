package com.example.tasksmspring.register;

import com.example.tasksmspring.database.DataBaseManagement;
import com.example.tasksmspring.users.User;
import com.example.tasksmspring.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final DataBaseManagement dataBaseManagement;

    private final UserRepository userRepository;

    public RegisterService(DataBaseManagement dataBaseManagement, UserRepository userRepository) {
        this.dataBaseManagement = dataBaseManagement;
        this.userRepository = userRepository;
    }

    public User register(Register request) {
        if(request.email().equals(userRepository.getUsername(1L))){
            throw new IllegalStateException("User already exists!");
        }
        return ResponseEntity.ok(dataBaseManagement.createUser(
                new User(request.firstName(), request.lastName()
                        ,request.password(), request.email()))).getBody();
    }
}
