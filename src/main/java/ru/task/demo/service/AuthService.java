package ru.task.demo.service;

import ru.task.demo.service.dto.auth.CreateUserDto;
import ru.task.demo.service.dto.auth.LoginRequest;
import ru.task.demo.service.dto.auth.LoginResponse;

/**
 * Сервис авторизации
 */
public interface AuthService {
    /**
     * Получить токен по логину и паролю
     * @param loginRequest дто авторизации
     * @return дто с токен
     */
    LoginResponse getAuthToken(LoginRequest loginRequest);

    /**
     * Создать пользователя
     * @param createUserDto дто регистрации
     * @return дто с токен
     */
    LoginResponse createUser(CreateUserDto createUserDto);
}
