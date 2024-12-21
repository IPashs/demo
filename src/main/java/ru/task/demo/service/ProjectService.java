package ru.task.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.task.demo.service.dto.SimpleIdNameDto;
import ru.task.demo.service.dto.project.CreateProjectRequest;
import ru.task.demo.service.dto.project.GetProjectResponse;
import ru.task.demo.service.dto.project.ModifyProjectRequest;

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
    SimpleIdNameDto createProject(CreateProjectRequest request);

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

    /**
     * Редактировать проект
     * @param projectId идентификатор проекта
     * @param modifyProjectRequest дто с новыми данными для проекта
     * @return дто с id и названием проекта
     */
    SimpleIdNameDto modifyProject(UUID projectId, ModifyProjectRequest modifyProjectRequest);
}
