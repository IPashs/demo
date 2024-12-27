package ru.task.demo.service.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * Dto с ответом от провайдера при получении поста по id провайдера
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetPostFromJSONPlaceholderResponse extends SimplePostDto{
    /**
     * Идентификатор поста от провайдера
     */
    private Long id;
    /**
     * Идентификатор пользователя от провайдера
     */
    //В данной реализации тестового задания не используется
    private Long userId;
}