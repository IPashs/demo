package ru.task.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@Table(name = "posts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    /**
     * Идентификатор поста
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    /**
     * Идентификатор поста у провайдера
     */
    //Id у провайдера представлен как BIGINT
    @NotNull
    @Column(name = "api_id", nullable = false)
    private Long apiId;
    /**
     * Заголовок поста
     */
    @NotNull
    @Column(name = "title", nullable = false, length = Integer.MAX_VALUE)
    private String title;
    /**
     * Тело поста
     */
    @NotNull
    @Column(name = "body", nullable = false, length = Integer.MAX_VALUE)
    private String body;
    /**
     * Пользователь, автор
     */
    //Моковый пользователь на стороне провайдера, id представлен как BIGINT.
    //В рамках тестового, не стоит задача на обеспечение согласованности баз данных с провайдером,
    //Поэтому пользователь будет всегда захардкожен текущим, авторизованным.
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}