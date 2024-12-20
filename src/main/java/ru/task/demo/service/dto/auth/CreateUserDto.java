package ru.task.demo.service.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto для регистрации пользователя
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String password;

}
