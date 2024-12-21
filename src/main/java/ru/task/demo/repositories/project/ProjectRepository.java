package ru.task.demo.repositories.project;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.task.demo.entity.Project;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий проекта
 */
@Repository
interface ProjectRepository extends JpaRepository<Project, UUID> {
    /**
     * Получить из бд проект с автором и разделами
     * @param projectId идентификатор проекта
     * @return id проекта
     */
    @EntityGraph(attributePaths = {"sections", "author"})
    Optional<Project> findWithDetailsById(UUID projectId);

    /**
     * Получить id проекта по id раздела, используется, чтобы проверить возможность удаления раздела.
     * @param sectionId id раздела
     * @return id проекта
     */
    @Query(value = """
        SELECT p.id
        FROM project p
        JOIN project_section ps ON ps.project_id = p.id
        JOIN section s ON s.id = ps.section_id
        WHERE s.id = :sectionId
        LIMIT 1
        """, nativeQuery = true)
    Optional<UUID> findAnyBySectionId(@Param("sectionId") UUID sectionId);
}
