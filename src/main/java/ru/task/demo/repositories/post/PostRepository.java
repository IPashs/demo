package ru.task.demo.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.task.demo.entity.Post;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий постов
 */
@Repository
interface PostRepository extends JpaRepository<Post, UUID> {
    /**
     * Получить пост по идентификатору провайдера если существует или ошибка
     *
     * @param apiId идентификатор поста в системе провайдера
     * @return сущность поста
     */
    Optional<Post> findByApiId(Long apiId);
}
