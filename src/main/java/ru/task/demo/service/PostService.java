package ru.task.demo.service;

import ru.task.demo.service.dto.post.GetPostResponse;

/**
 * Сервис постов
 */
public interface PostService {
    /**
     * Получить пост по идентификатору от провайдера
     * @param postId Идентификатор поста
     * @return ДТО ответа с постом
     */
    GetPostResponse getPost(Long postId);
}
