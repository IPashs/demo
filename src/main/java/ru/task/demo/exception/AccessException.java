package ru.task.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccessException extends RuntimeException {
    private final String message;

    public AccessException() {
        message = "access exception";
    }

    public AccessException(String message) {
        this.message = message;
    }
}
