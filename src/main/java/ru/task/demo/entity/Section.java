package ru.task.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Сущность раздела
 */
@Getter
@Entity
@Table(name = "section")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Section {
    /**
     * Идентификатор раздела
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    /**
     * Название раздела
     */
    @NotNull
    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;
    /**
     * Дата создания записи о разделе
     */
    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}