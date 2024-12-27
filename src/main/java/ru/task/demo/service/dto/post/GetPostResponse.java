package ru.task.demo.service.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * Dto с ответом при получении поста по id
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetPostResponse extends SimplePostDto{
    /**
     * Идентификатор поста
     */
    private UUID postId;
    /**
     * Идентификатор пользователя
     */
    private UUID userId;
}
