package ru.task.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NoSuchElementException extends RuntimeException {
    private final String message;

    public NoSuchElementException() {
        message = "element not found";
    }

    public NoSuchElementException(String message) {
        this.message = message;
    }
}
