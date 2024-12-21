package ru.task.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.task.demo.entity.User;

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
    /**
     * Получить текущего пользователя
     * @return пользователь
     */
    User getCurrentUser() throws UsernameNotFoundException;
    /**
     * Получить пользователя по почте
     * @param email почта
     * @return пользователь
     */
    User getUserByEmail(String email) throws UsernameNotFoundException;

}
