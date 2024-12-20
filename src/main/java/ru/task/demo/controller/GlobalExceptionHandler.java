package ru.task.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.task.demo.exception.AppError;
import ru.task.demo.exception.handlers.ExceptionHandlerStrategy;

import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Map<Class<? extends RuntimeException>, ExceptionHandlerStrategy> exceptionHandlerStrategies;

    @Autowired
    public GlobalExceptionHandler(Map<Class<? extends RuntimeException>, ExceptionHandlerStrategy> exceptionHandlerStrategies) {
        this.exceptionHandlerStrategies = exceptionHandlerStrategies;
    }

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<AppError> customHandleException(Exception ex, WebRequest request) {
        return Optional.ofNullable(exceptionHandlerStrategies.get(ex.getClass()))
                .map(exStrategy -> exStrategy.handleException(ex, request))
                .orElseGet(()-> {
                    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
                    return new ResponseEntity<>(new AppError(ex.getMessage()), new HttpHeaders(), status);
                });
    }
}