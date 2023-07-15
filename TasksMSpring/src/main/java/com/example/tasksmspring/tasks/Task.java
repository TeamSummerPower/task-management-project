package com.example.tasksmspring.tasks;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Task {
    private String title;
    private String description;
    private boolean isRec;
    private int userId;
    private Priority priority;

    public Task(String title, String description, boolean isRec, int userId, Priority priority) {
        this.title = title;
        this.description = description;
        this.isRec = isRec;
        this.userId = userId;
        this.priority = priority;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
