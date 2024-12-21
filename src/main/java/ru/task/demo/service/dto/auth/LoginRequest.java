package ru.task.demo.service.dto.auth;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

/**
 * Dto для авторизации пользователя
 */
@Getter
@Setter
public class LoginRequest {

    @Email
    private String email;
    private String password;
}
