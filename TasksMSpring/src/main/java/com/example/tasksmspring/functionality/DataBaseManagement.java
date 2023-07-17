package com.example.tasksmspring.functionality;

import com.example.tasksmspring.tasks.Task;
import com.example.tasksmspring.tasks.TaskRepository;
import com.example.tasksmspring.users.User;
import com.example.tasksmspring.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataBaseManagement {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public DataBaseManagement(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }
    public Task createTask (Task task) {
        return taskRepository.save(task);
    }
    public void deleteTask(List<Long> taskIds) {
        taskRepository.deleteAllById(taskIds);
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public Optional<User> getUserByUserName(User user) {
        return userRepository.findByUserName(user.getUserName());
    }
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
    public void updateUser(User user) {
        userRepository.save(user);
    }
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

}
