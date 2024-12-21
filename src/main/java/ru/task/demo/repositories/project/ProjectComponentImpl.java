package ru.task.demo.repositories.project;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.task.demo.entity.Project;

import java.util.Optional;

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
}
