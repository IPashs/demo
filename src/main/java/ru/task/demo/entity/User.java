package ru.task.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.task.demo.util.Role;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Сущность пользователя
 */
@Getter
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * Идентификатор пользователя
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    /**
     * Пароль пользователя
     */
    @Column(name = "password")
    @NotNull
    private String password;
    /**
     * Имя пользователя
     */
    @Column(name = "name")
    @NotNull
    private String name;
    /**
     * Электронная почта пользователя
     */
    @Column(name = "email")
    @NotNull
    private String email;
    /**
     * Роль пользователя
     */
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;
    /**
     *  Дата регистрации пользователя
     */
    @Column(name = "registered_at")
    @NotNull
    @Builder.Default
    private LocalDateTime registeredAt = LocalDateTime.now();

}
