package ru.task.demo.service.dto.project;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Dto для создания проекта
 */
@Data
public class CreateProjectRequest {
    private String name;
    private String code;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<UUID> sections;
    private String authorEmail;
}
