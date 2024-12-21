package ru.task.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RequestException extends RuntimeException {
    private final String message;

    public RequestException() {
        message = "bad request";
    }

    public RequestException(String message) {
        this.message = message;
    }
}
