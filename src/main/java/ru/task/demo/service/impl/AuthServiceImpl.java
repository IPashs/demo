package ru.task.demo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.task.demo.config.PasswordEncoderConfiguration;
import ru.task.demo.entity.User;
import ru.task.demo.exception.AuthException;
import ru.task.demo.exception.RequestException;
import ru.task.demo.repositories.UserComponent;
import ru.task.demo.service.AuthService;
import ru.task.demo.service.UserService;
import ru.task.demo.service.dto.CreateUserDto;
import ru.task.demo.service.dto.LoginRequest;
import ru.task.demo.service.dto.LoginResponse;
import ru.task.demo.util.JwtUtil;
import ru.task.demo.util.Role;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserComponent userComponent;
    private final PasswordEncoderConfiguration passwordEncoderConfiguration;

    private static final String INCORRECT_LOGIN_OR_PASSWORD_MESSAGE = "Incorrect login or password";
    private static final String USER_EXISTS = "this user already exists";


    @Override
    public LoginResponse getAuthToken(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new AuthException(INCORRECT_LOGIN_OR_PASSWORD_MESSAGE);
        }
        return createJwtResponse(loginRequest.getEmail());
    }

    private String createAuthTokenByLogin(String email) {
        UserDetails userDetails = userService.loadUserByUsername(email);
        return jwtUtil.generateAccessToken(userDetails);
    }

    private LoginResponse createJwtResponse(String email) {
        return LoginResponse.builder()
            .accessToken(createAuthTokenByLogin(email))
            .build();
    }

    @Override
    public LoginResponse createUser(CreateUserDto createUserDto) {
        Optional<User> userOpt = userComponent.findByEmail(createUserDto.getEmail());
        if (userOpt.isEmpty()) {

            User savedUser = userComponent.save(User.builder()
                .name(createUserDto.getName())
                .email(createUserDto.getEmail())
                .role(Role.USER)
                .password(passwordEncoderConfiguration.passwordEncoder().encode(createUserDto.getPassword()))
                .build());

            return createJwtResponse(savedUser.getEmail());
        } else {
            throw new RequestException(USER_EXISTS);
        }
    }
}


