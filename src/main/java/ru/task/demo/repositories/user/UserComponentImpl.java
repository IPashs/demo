package ru.task.demo.repositories.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.task.demo.entity.User;
import ru.task.demo.exception.NoSuchElementException;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class UserComponentImpl implements UserComponent {
    private final UserRepository userRepository;

    @Override
    public User findByEmailOrDie(final String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new NoSuchElementException("пользователь не найден"));
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(final User user) {
        return userRepository.save(user);
    }
}
