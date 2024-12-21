package ru.task.demo.repositories.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.task.demo.entity.Project;

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
}
