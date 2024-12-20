package ru.task.demo.exception.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import ru.task.demo.exception.AppError;
import ru.task.demo.exception.NoSuchElementException;


@Component
public class NoSuchElementExceptionHandler implements ExceptionHandlerStrategy {

    @Override
    public Class<? extends RuntimeException> getSupportedException() {
        return NoSuchElementException.class;
    }

    @Override
    public ResponseEntity<AppError> handleException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new AppError(ex.getMessage()), new HttpHeaders(), status);
    }
}