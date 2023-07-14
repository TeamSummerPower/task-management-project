package TaskManagment.Users;

import TaskManagment.Tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int counter = 1;
    private int id;
    private String name;
    private String userName;
    private String password;
    private String email;
    private Role role;
    private List<Task> newTasks;
    public User(String name, String userName, String password, String email, Role role) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.newTasks = new ArrayList<>();
        this.id = counter++;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public Role getRole() {
        return role;
    }
    public List<Task> getNewTasks() {
        return newTasks;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
