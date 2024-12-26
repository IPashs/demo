package ru.task.demo.service;


import ru.task.demo.service.dto.post.GetPostFromJSONPlaceholderResponse;

/**
 * Интерфейс взаимодействия с провайдером JSONPlaceholder
 */
public interface JSONPlaceholderService {
    /**
     * Получить пост от провайдера по его идентификатору
     * @param postId идентификатор поста от провайдера
     * @return ДТО поста от провайдера
     */
    GetPostFromJSONPlaceholderResponse getPost(Long postId);
}
