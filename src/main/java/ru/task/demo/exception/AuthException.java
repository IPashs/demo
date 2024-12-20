package ru.task.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthException extends RuntimeException {
    private final String message;

    public AuthException() {
        message = "auth error";
    }

    public AuthException(String message) {
        this.message = message;
    }
}