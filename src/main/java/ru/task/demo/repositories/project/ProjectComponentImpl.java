package ru.task.demo.repositories.project;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.task.demo.entity.Project;
import ru.task.demo.exception.NoSuchElementException;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectComponentImpl implements ProjectComponent {
    private final ProjectRepository projectRepository;

    @Override
    public Project save(final Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Page<Project> getAllProjects(final PageRequest pageRequest) {
        return projectRepository.findAll(pageRequest);
    }

    @Override
    public Project getProjectWithSectionAndAuthorOrDie(final UUID projectId) {
        return projectRepository.findWithDetailsById(projectId)
            .orElseThrow(() -> new NoSuchElementException("Проект не найден"));
    }

    @Override
    public Project getProjectOrDie(final UUID projectId) {
        return projectRepository.findById(projectId)
            .orElseThrow(() -> new NoSuchElementException("Проект не найден"));
    }

    @Override
    public Optional<UUID> getAnyProjectWithSection(final UUID sectionId) {
        return projectRepository.findAnyBySectionId(sectionId);
    }
}
