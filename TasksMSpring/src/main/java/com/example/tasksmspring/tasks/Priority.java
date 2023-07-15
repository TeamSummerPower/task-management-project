package com.example.tasksmspring.tasks;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public enum Priority {
    NOT_IMPORTANT, IMPORTANT, VERY_IMPORTANT, MANDATORY;
}
