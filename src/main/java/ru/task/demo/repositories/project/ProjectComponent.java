package ru.task.demo.repositories.project;

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
}
