package com.example.tasksmspring.users;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public enum Role {
    USER, ADMIN, OWNER;
}
