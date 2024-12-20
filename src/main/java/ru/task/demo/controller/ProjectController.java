package ru.task.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.task.demo.service.ProjectService;
import ru.task.demo.service.dto.project.CreateProjectRequest;

/**
 * Контроллер с методами проекта
 */
@RestController
@RequiredArgsConstructor
@RequestMapping
public class ProjectController {
    private final ProjectService projectService;

    /**
     * Метод создания проекта
     * @param createProjectRequest дто для создания проекта
     * @return дто с id и названием проекта
     */
    @PostMapping("/project")
    public ResponseEntity<Object> loginUsernamePassword(
        @RequestBody @Valid CreateProjectRequest createProjectRequest) {
        return ResponseEntity.ok(projectService.createProject(createProjectRequest));
    }
}
