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
import ru.task.demo.util.ProjectStatus;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectComponent projectComponent;
    private final UserService userService;
    private final SectionComponent sectionComponent;

    @Override
    public CreateProjectResponse createProject(final CreateProjectRequest request) {
        Project savedProject = this.projectComponent.save(this.buildProject(request));

        return CreateProjectResponse.builder()
            .projectName(savedProject.getName())
            .projectId(savedProject.getId())
            .build();
    }

    @Override
    public Page<Object> getProjects(final PageRequest pageRequest) {
        return projectComponent.getAllProjects(pageRequest)
            .map(e-> new SimpleIdNameDto(e.getId(), e.getName()));
    }

    private Project buildProject(final CreateProjectRequest request) {
        return Project.builder()
            .code(request.getCode())
            .name(request.getName())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .author(userService.getUserByEmail(request.getAuthorEmail()))
            .createdAt(LocalDateTime.now())
            .editor(userService.getCurrentUser())
            .status(ProjectStatus.NEW)
            .sections(sectionComponent.getSectionsOrDie(request.getSections()))
            .build();
    }
}
