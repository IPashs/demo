package ru.task.demo.service.dto.project;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.task.demo.util.ProjectStatus;

/**
 * Dto для редактирования проекта
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ModifyProjectRequest extends CreateProjectRequest {
    private ProjectStatus status;
}
