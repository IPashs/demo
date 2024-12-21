package ru.task.demo.repositories.project;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.task.demo.entity.Project;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий проекта
 */
@Repository
interface ProjectRepository extends JpaRepository<Project, UUID> {
    @EntityGraph(attributePaths = {"sections", "author"})
    Optional<Project> findById(UUID projectId);
}
