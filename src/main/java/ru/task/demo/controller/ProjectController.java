package ru.task.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.task.demo.service.ProjectService;
import ru.task.demo.service.dto.SearchPage;
import ru.task.demo.service.dto.project.CreateProjectRequest;
import ru.task.demo.service.dto.project.ModifyProjectRequest;

import java.util.UUID;

/**
 * Контроллер с методами проекта
 */
@RestController
@RequiredArgsConstructor
@RequestMapping
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/projects")
    public ResponseEntity<Object> createProject(
        @RequestBody @Valid CreateProjectRequest createProjectRequest) {
        return new ResponseEntity<>(projectService.createProject(createProjectRequest), HttpStatus.CREATED);
    }

    @GetMapping("/projects")
    public ResponseEntity<Object> getAllProjects(
        @RequestParam(value = "pageNumber", required = false, defaultValue = "0") final Integer pageNumber,
        @RequestParam(value = "pageSize", required = false, defaultValue = "100") final Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        return ResponseEntity.ok(SearchPage.fromPage(projectService.getProjects(pageRequest)));
    }

    @GetMapping("/projects/{project_id}")
    public ResponseEntity<Object> getProject(
        @PathVariable(value = "project_id") final UUID projectId) {
        return ResponseEntity.ok(projectService.getProject(projectId));
    }

    @PutMapping("/projects/{project_id}")
    public ResponseEntity<Object> modifyProject(
        @PathVariable(value = "project_id") final UUID projectId,
        @RequestBody @Valid ModifyProjectRequest modifyProjectRequest) {
        return new ResponseEntity<>(
            projectService.modifyProject(projectId, modifyProjectRequest),
            HttpStatus.CREATED);
    }

    @DeleteMapping("/projects/{project_id}")
    public ResponseEntity<Object> deleteProject(
        @PathVariable(value = "project_id") final UUID projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok().build();
    }
}
