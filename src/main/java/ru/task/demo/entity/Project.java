package ru.task.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.task.demo.util.ProjectStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Сущность проекта
 */
@Getter
@Setter
@Builder
@Entity
@Table(name = "project")
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    /**
     * Идентификатор проекта
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    /**
     * Название проекта
     */
    @Column(name = "name")
    @NotNull
    private String name;
    /**
     * Шифр проекта
     */
    @Column(name = "code")
    @NotNull
    private String code;
    /**
     * Дата начала проекта
     */
    @Column(name = "start_date")
    @NotNull
    private LocalDateTime startDate;
    /**
     * Дата окончания проекта
     */
    @Column(name = "end_date")
    @NotNull
    private LocalDateTime endDate;

    /**
     * Автор проекта
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    /**
     * Дата создания записи о проекте
     */
    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Дата обновления записи о проекте
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Лицо изменившее запись о проекте
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editor_id")
    private User editor;

    /**
     * Статус проекта
     */
    @NotNull
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    /**
     * Список разделов проекта
     */
    @ManyToMany
    @JoinTable(name = "project_section",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "section_id"))
    private List<Section> sections = new ArrayList<>();
}
