package ru.task.demo.service.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Dto для создания проекта
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectRequest {
    /**
     * Название проекта
     */
    @NotBlank
    private String name;
    /**
     * Шифр проекта
     */
    @NotBlank
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
     * Список разделов проекта
     */
    private List<UUID> sections;
    /**
     * Электронная почта автора проекта
     */
    @NotBlank
    private String authorEmail;
}
