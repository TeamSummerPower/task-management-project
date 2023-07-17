package com.example.tasksmspring.users;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    USER("USER"), ADMIN("ADMIN"), OWNER("OWNER");

    @JsonCreator
    Role(String s) {

    }
}
