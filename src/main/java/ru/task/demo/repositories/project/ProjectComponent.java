package ru.task.demo.repositories.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.task.demo.entity.Project;

import java.util.Optional;
import java.util.UUID;

/**
 * Компонент проекта
 */
public interface ProjectComponent {
    /**
     * Сохранить проект
     * @param project сохраняемый проект
     * @return сохраненный проект
     */
    Project save(Project project);
    /**
     * Получить все проекты с пагинацией
     * @param pageRequest пагинация
     * @return страница проектов
     */
    Page<Project> getAllProjects(PageRequest pageRequest);

    /**
     * Получить проект с разделами из БД по идентификатору если существует иначе ошибка
     * @param projectId идентификатор проекта
     * @return проект
     */
    Project getProjectWithSectionAndAuthorOrDie(UUID projectId);

    /**
     * Получить проект если существует иначе ошибка
     * @param projectId идентификатор проекта
     * @return проект
     */
    Project getProjectOrDie(UUID projectId);

    /**
     * Получить любой проект с разделом
     * @param sectionId идентификатор раздела
     * @return id проекта
     */
    Optional<UUID> getAnyProjectWithSection(UUID sectionId);
}
