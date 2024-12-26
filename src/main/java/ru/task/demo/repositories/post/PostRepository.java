package ru.task.demo.repositories.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.task.demo.entity.Post;

import java.util.UUID;

/**
 * Репозиторий постов
 */
@Repository
interface PostRepository extends JpaRepository<Post, UUID> {
}
