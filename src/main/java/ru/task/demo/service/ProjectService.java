package ru.task.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.task.demo.service.dto.project.CreateProjectRequest;
import ru.task.demo.service.dto.project.CreateProjectResponse;
import ru.task.demo.service.dto.project.GetProjectResponse;

import java.util.UUID;

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

    /**
     * Получить страницу со всеми проектами
     * @param pageRequest пагинация
     * @return страница с проектами
     */
    Page<Object> getProjects(PageRequest pageRequest);

    /**
     * Получить дто с данными проекта по его id
     * @param projectId идентификатор проекта
     * @return дто с данными проекта
     */
    GetProjectResponse getProject(UUID projectId);
}
