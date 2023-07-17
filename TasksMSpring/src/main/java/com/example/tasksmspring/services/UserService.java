package com.example.tasksmspring.services;

import com.example.tasksmspring.users.User;
import com.example.tasksmspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public Optional<User> getUserByUserName(User user) {
        return userRepository.findByUserName(user.getUserName());
    }
    public void updateUser(User user) {
        userRepository.save(user);
    }
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
