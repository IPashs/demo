package ru.task.demo.service.dto.post;

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
    private String title;
    /**
     * Тело поста
     */
    private String body;
}
