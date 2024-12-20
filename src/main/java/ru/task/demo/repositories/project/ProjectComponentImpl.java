package ru.task.demo.repositories.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.task.demo.entity.Project;

@Component
@RequiredArgsConstructor
public class ProjectComponentImpl implements ProjectComponent {
    private final ProjectRepository projectRepository;

    @Override
    public Project save(final Project project) {
        return projectRepository.save(project);
    }
}
