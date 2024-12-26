package ru.task.demo.service;

import ru.task.demo.service.dto.post.GetPostResponse;
import ru.task.demo.service.dto.post.SimplePostDto;

/**
 * Сервис постов
 */
public interface PostService {
    /**
     * Получить пост по идентификатору от провайдера
     *
     * @param postId Идентификатор поста
     * @return ДТО ответа с постом
     */
    GetPostResponse getPost(Long postId);

    /**
     * Создать пост
     *
     * @param createPostRequest ДТО с данными для создания поста
     * @return ДТО ответа с постом
     */
    //не проверяем уникальность постов
    GetPostResponse createPost(SimplePostDto createPostRequest);
}
