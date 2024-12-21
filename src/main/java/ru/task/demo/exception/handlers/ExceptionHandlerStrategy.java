package ru.task.demo.exception.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import ru.task.demo.exception.AppError;

public interface ExceptionHandlerStrategy {

    Class<? extends RuntimeException> getSupportedException();

    ResponseEntity<AppError> handleException(Exception ex, WebRequest request);
}
