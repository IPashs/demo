package ru.task.demo.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.task.demo.entity.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с пользователем
 */
@Repository
interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Получить пользователя по электронной почте
     * @param email электронная почта
     * @return пользователь
     */
    Optional<User> findByEmail(String email);
}
