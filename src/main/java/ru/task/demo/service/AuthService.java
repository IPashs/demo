package ru.task.demo.service;

import ru.task.demo.service.dto.CreateUserDto;
import ru.task.demo.service.dto.LoginRequest;
import ru.task.demo.service.dto.LoginResponse;

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
