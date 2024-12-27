package ru.task.demo.service.dto.post;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Дто с основными данными о посте
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SimplePostDto {
    /**
     * Заголовок поста
     */
    @NotBlank
    private String title;
    /**
     * Тело поста
     */
    @NotBlank
    private String body;
}
