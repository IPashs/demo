package ru.task.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.task.demo.entity.Project;
import ru.task.demo.repositories.project.ProjectComponent;
import ru.task.demo.repositories.section.SectionComponent;
import ru.task.demo.service.ProjectService;
import ru.task.demo.service.UserService;
import ru.task.demo.service.dto.SimpleIdNameDto;
import ru.task.demo.service.dto.project.CreateProjectRequest;
import ru.task.demo.service.dto.project.CreateProjectResponse;
import ru.task.demo.service.dto.project.GetProjectResponse;
import ru.task.demo.util.ProjectStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectComponent projectComponent;
    private final UserService userService;
    private final SectionComponent sectionComponent;

    @Override
    public CreateProjectResponse createProject(final CreateProjectRequest request) {
        Project savedProject = this.projectComponent.save(
            Project.builder()
                .code(request.getCode())
                .name(request.getName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .author(userService.getUserByEmail(request.getAuthorEmail()))
                .createdAt(LocalDateTime.now())
                .editor(userService.getCurrentUser())
                .status(ProjectStatus.NEW)
                .sections(sectionComponent.getSectionsOrDie(request.getSections()))
                .build());

        return CreateProjectResponse.builder()
            .projectName(savedProject.getName())
            .projectId(savedProject.getId())
            .build();
    }

    @Override
    public Page<Object> getProjects(final PageRequest pageRequest) {
        return projectComponent.getAllProjects(pageRequest)
            .map(e -> new SimpleIdNameDto(e.getId(), e.getName()));
    }

    @Override
    public GetProjectResponse getProject(final UUID projectId) {
        Project project = projectComponent.getProjectWithSectionAndAuthorOrDie(projectId);
        return GetProjectResponse.builder()
            .projectId(project.getId())
            .code(project.getCode())
            .name(project.getName())
            .status(project.getStatus())
            .startDate(project.getStartDate())
            .endDate(project.getEndDate())
            .author(new SimpleIdNameDto(project.getAuthor().getId(), project.getAuthor().getName()))
            .sections(project.getSections().stream()
                .map(e -> new SimpleIdNameDto(e.getId(), e.getName()))
                .toList())
            .build();
    }
}
