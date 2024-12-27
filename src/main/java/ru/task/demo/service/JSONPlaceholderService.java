package ru.task.demo.service;


import ru.task.demo.service.dto.post.GetPostFromJSONPlaceholderResponse;
import ru.task.demo.service.dto.post.SimplePostDto;

/**
 * Интерфейс взаимодействия с провайдером JSONPlaceholder
 */
public interface JSONPlaceholderService {
    /**
     * Получить пост от провайдера по его идентификатору
     *
     * @param postId идентификатор поста от провайдера
     * @return ДТО поста от провайдера
     */
    GetPostFromJSONPlaceholderResponse getPost(Long postId);

    /**
     * Создать пост у провайдера
     *
     * @param createPostRequest ДТО с данными для создания поста
     * @return ДТО поста от провайдера
     */
    GetPostFromJSONPlaceholderResponse createPost(SimplePostDto createPostRequest);
}
