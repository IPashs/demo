package ru.task.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.task.demo.entity.User;
import ru.task.demo.exception.RequestException;
import ru.task.demo.service.UserService;
import ru.task.demo.service.dto.auth.CreateUserDto;

import java.util.Optional;

//по хорошему тут нужно выносить логику в сервисы, что требует времени,
//ознакомиться с должным результатом можно в сервисе AuthController
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(
        @RequestBody @Valid CreateUserDto createUserDto) {
        Optional<User> user = userService.findUserByEmail(createUserDto.getEmail()); //предположим такой метод есть
        if (user.isPresent()) {
            throw new RequestException("User already exists"); //здесь кинул 400ошибку,
            // чтобы кинуть конфликт, нужно добавить такую кастомную ошибку и написать под нее свое стратегию
        }

        userService.saveUser(user); //предположим такой метод есть
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED); //предположим что у нас ОКЕЙ возвращать строку при создании и так хотел аналитик
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable(name = "user_id") Long userId) {
        User user = userService.findUserByIdOrDie(userId); //ошибку отправить на уровне компонента если пользователь не найден

        return ResponseEntity.ok(UserResponseDto.of(user)); //предположим тут отправили dto с ответом
    }

    @PutMapping("/users/{user_id}")
    public ResponseEntity<String> updateUser(@PathVariable(name = "user_id") Long userId,
                                             @RequestBody @Valid CreateUserDto createUserDto) {
        User user = userService.findUserByIdOrDie(userId); //ошибку отправить на уровне компонента если пользователь не найден

        //Валидация в дто через @NotBlank или @Email например
        user.setName(user.getName());
        user.setEmail(user.getEmail());

        userService.saveUser(user);
        //предположим что у нас ОКЕЙ возвращать строку при создании и так хотел аналитик
        return new ResponseEntity<>("User updated successfully", HttpStatus.CREATED);
    }
}
