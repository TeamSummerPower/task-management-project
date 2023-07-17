package com.example.tasksmspring.tasks;

import com.example.tasksmspring.users.User;
import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "is_rec")
    private boolean isRec;
    @MapsId
    @ManyToOne
    private User author;
    @Column(name = "priority")
    private Priority priority;

    public Task(Long id, String title, String description, boolean isRec, User author, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isRec = isRec;
        this.author = author;
        this.priority = priority;
    }

    public Task() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRec() {
        return isRec;
    }

    public void setRec(boolean rec) {
        isRec = rec;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User user) {
        this.author = user;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
