package ru.task.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.task.demo.entity.User;
import ru.task.demo.repositories.user.UserComponent;
import ru.task.demo.service.UserService;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserComponent userComponent;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userComponent.findByEmailOrDie(email);
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    @Override
    public User getCurrentUser() throws UsernameNotFoundException {
        return userComponent.findByEmailOrDie(
            SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public User getUserByEmail(final String email) throws UsernameNotFoundException {
        return userComponent.findByEmailOrDie(email);
    }
}
