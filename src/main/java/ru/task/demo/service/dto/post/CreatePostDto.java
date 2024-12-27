package ru.task.demo.service.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Дто для создания поста у провайдера
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreatePostDto extends SimplePostDto {
    /**
     * Идентификатор пользователя, автора в системе провайдера
     */
    //будет всегда константа, базы пользователя с провайдером не согласованы в рамках тестового
    private Long userId;
}
