package ru.task.demo.repositories.post;

import ru.task.demo.entity.Post;

import java.util.Optional;

/**
 * Компонент постов
 */
public interface PostComponent {
    /**
     * Получить пост по идентификатору провайдера
     *
     * @param postId идентификатор поста в системе провайдера
     * @return сущность поста
     */
    //намеренный поиск по id без индекса, будет исправлено в следующем пункте тестового
    Optional<Post> findByApiId(Long postId);

    /**
     * Сохранить пост в БД
     * @param post сущность поста
     * @return сохраненная сущность поста
     */
    Post save(Post post);
}
