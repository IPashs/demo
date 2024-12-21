package ru.task.demo.service.dto.project;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.task.demo.service.dto.SimpleIdNameDto;
import ru.task.demo.util.ProjectStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetProjectResponse {
    /**
     * Идентификатор проекта
     */
    @NotNull
    private UUID projectId;
    /**
     * Название проекта
     */
    @NotNull
    private String name;
    /**
     * Шифр проекта
     */
    @NotNull
    private String code;
    /**
     * Дата начала проекта
     */
    @NotNull
    private LocalDateTime startDate;
    /**
     * Дата окончания проекта
     */
    @NotNull
    private LocalDateTime endDate;

    /**
     * Идентификатор и имя автора проекта
     */
    @NotNull
    private SimpleIdNameDto author;

    /**
     * Статус проекта
     */
    @NotNull
    private ProjectStatus status;

    /**
     * Список разделов проекта
     */
    private List<SimpleIdNameDto> sections;
}
