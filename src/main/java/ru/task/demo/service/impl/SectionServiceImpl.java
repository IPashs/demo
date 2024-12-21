package ru.task.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.task.demo.entity.Section;
import ru.task.demo.exception.RequestException;
import ru.task.demo.repositories.project.ProjectComponent;
import ru.task.demo.repositories.section.SectionComponent;
import ru.task.demo.service.SectionService;
import ru.task.demo.service.dto.SimpleIdNameDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {
    private final SectionComponent sectionComponent;
    private final ProjectComponent projectComponent;

    @Override
    public Page<SimpleIdNameDto> getSections(final PageRequest pageRequest) {
        return sectionComponent.getAllSections(pageRequest)
            .map(e -> new SimpleIdNameDto(e.getId(), e.getName()));
    }

    @Override
    public void createSection(final String sectionName) {
        sectionComponent.save(Section.builder()
            .name(sectionName)
            .createdAt(LocalDateTime.now())
            .build());
    }

    @Override
    public void deleteSection(final UUID sectionId) {
        if (projectComponent.getAnyProjectWithSection(sectionId).isPresent()) {
            throw new RequestException("раздел не может быть удален т.к. используется в проекте");
        }
        sectionComponent.deleteOrDie(sectionId);
    }
}
