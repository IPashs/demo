package ru.task.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Простое dto id / name
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleIdNameDto {
    /**
     * Id объекта
     */
    private UUID id;
    /**
     * Название объекта
     */
    private String name;
}
