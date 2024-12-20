package ru.task.demo.repositories;

import ru.task.demo.entity.User;

import java.util.Optional;


/**
 * Компонент для работы с сущностью пользователя
 */
public interface UserComponent {
    /**
     * Получить пользователя или ошибка
     *
     * @param email почта пользователя
     * @return сущность пользователя
     */
    User findByEmailOrDie(String email);
    /**
     * Получить пользователя
     *
     * @param email почта пользователя
     * @return сущность пользователя
     */
    Optional<User> findByEmail(String email);

    /**
     * сохранить пользователя в бд
     * @param user сущность пользователя
     * @return сохраненная сущность пользователя
     */
    User save(User user);
}
