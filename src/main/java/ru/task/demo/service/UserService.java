package ru.task.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Сервис работы с пользователем
 */
public interface UserService extends UserDetailsService {
    /**
     * загрузить UserDetails по почте (username)
     * @param email почта пользователя
     * @return userDetails
     */
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}
