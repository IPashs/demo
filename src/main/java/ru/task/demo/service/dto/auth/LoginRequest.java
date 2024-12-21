package ru.task.demo.service.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Dto для авторизации пользователя
 */
@Getter
@Setter
public class LoginRequest {
    /**
     * Электронная почта пользователя
     */
    @NotBlank
    @Email
    private String email;
    /**
     * Пароль пользователя
     */
    @NotBlank
    private String password;
}
