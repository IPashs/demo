package ru.task.demo.exception;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class AppError {
    private String message;
    private LocalDateTime timestamp;

    public AppError(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
