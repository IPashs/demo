package ru.task.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

/**
 * Сущность раздела
 */
@Getter
@Setter
@Entity
@Table(name = "section")
public class Section {
    /**
     * Идентификатор раздела
     */
    @Id
    @Column(name = "id", nullable = false)
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
    private Instant createdAt;
}