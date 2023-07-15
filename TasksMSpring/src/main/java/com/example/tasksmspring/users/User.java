package com.example.tasksmspring.users;

import com.example.tasksmspring.tasks.Task;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize
public class User {
    private static int cnt = 0;
    private int id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private List<Task> newTasks;

    public User(String name, String email, String password, Role role) {
        this.id = cnt++;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.newTasks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public List<Task> getNewTasks() {
        return newTasks;
    }
}
