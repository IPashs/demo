package ru.task.demo.service;

import ru.task.demo.service.dto.project.CreateProjectRequest;
import ru.task.demo.service.dto.project.CreateProjectResponse;

/**
 * Сервис проекта
 */
public interface ProjectService {
    /**
     * Метод создания проекта
     * @param request дто для создания проекта
     * @return дто с id и названием проекта
     */
    CreateProjectResponse createProject(CreateProjectRequest request);
}
