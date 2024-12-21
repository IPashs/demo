package ru.task.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.task.demo.service.AuthService;
import ru.task.demo.service.dto.auth.CreateUserDto;
import ru.task.demo.service.dto.auth.LoginRequest;

/**
 * Контроллер авторизации
 */
@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController {
    private final AuthService authService;

    /**
     * Метод авторизации пользователя
     * @param loginRequest дто для авторизации
     * @return дто с токеном доступа
     */
    @PostMapping("/auth")
    public ResponseEntity<Object> loginUsernamePassword(
        @RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.getAuthToken(loginRequest));
    }

    /**
     * Метод для регистрации пользователя
     * @param createUserDto дто для регистрации
     * @return дто с токеном доступа
     */
    @PostMapping("/reg")
    public ResponseEntity<Object> registration(
        @RequestBody @Valid CreateUserDto createUserDto) {
        return new ResponseEntity<>(authService.createUser(createUserDto), HttpStatus.CREATED);
    }

}
