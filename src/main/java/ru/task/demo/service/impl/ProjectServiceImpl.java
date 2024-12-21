package ru.task.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.task.demo.entity.Project;
import ru.task.demo.exception.RequestException;
import ru.task.demo.repositories.project.ProjectComponent;
import ru.task.demo.repositories.section.SectionComponent;
import ru.task.demo.service.ProjectService;
import ru.task.demo.service.UserService;
import ru.task.demo.service.dto.SimpleIdNameDto;
import ru.task.demo.service.dto.project.CreateProjectRequest;
import ru.task.demo.service.dto.project.GetProjectResponse;
import ru.task.demo.service.dto.project.ModifyProjectRequest;
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
    public SimpleIdNameDto createProject(final CreateProjectRequest request) {
        Project savedProject = this.projectComponent.save(this.buildProject(request));

        return new SimpleIdNameDto(savedProject.getId(), savedProject.getName());
    }

    private Project buildProject(final CreateProjectRequest request) {
        return Project.builder()
            .code(request.getCode())
            .name(request.getName())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .createdAt(LocalDateTime.now())
            .author(userService.getUserByEmail(request.getAuthorEmail()))
            .editor(userService.getCurrentUser())
            .status(ProjectStatus.NEW)
            .sections(sectionComponent.getSectionsOrDie(request.getSections()))
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

    @Override
    public SimpleIdNameDto modifyProject(final UUID projectId, final ModifyProjectRequest modifyProjectRequest) {
        Project project = this.projectComponent.getProjectOrDie(projectId);
        if (ProjectStatus.DELETED.equals(modifyProjectRequest.getStatus())) {
            throw new RequestException("для удаления проекта следует использовать соответствующий метод");
        }
        //не очень красивое решение без использования mupstruct или другого маппера*
        project.setUpdatedAt(LocalDateTime.now());
        project.setCode(modifyProjectRequest.getCode());
        project.setName(modifyProjectRequest.getName());
        project.setStartDate(modifyProjectRequest.getStartDate());
        project.setEndDate(modifyProjectRequest.getEndDate());
        project.setAuthor(userService.getUserByEmail(modifyProjectRequest.getAuthorEmail()));
        project.setEditor(userService.getCurrentUser());
        project.setStatus(modifyProjectRequest.getStatus());
        project.setSections(sectionComponent.getSectionsOrDie(modifyProjectRequest.getSections()));
        Project savedProject = projectComponent.save(project);
        return new SimpleIdNameDto(savedProject.getId(), savedProject.getName());
    }

    @Override
    public void deleteProject(final UUID projectId) {
        Project project = this.projectComponent.getProjectOrDie(projectId);
        if (!ProjectStatus.DELETED.equals(project.getStatus())) {
            project.setUpdatedAt(LocalDateTime.now());
            project.setEditor(userService.getCurrentUser());
            project.setStatus(ProjectStatus.DELETED);
            projectComponent.save(project);
        }
    }
}
