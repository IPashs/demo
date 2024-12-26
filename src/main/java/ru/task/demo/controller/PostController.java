package ru.task.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.task.demo.service.PostService;

/**
 * Контроллер с методами постов
 */
@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostController {
    private final PostService postService;

    @GetMapping("/posts/{post_id}")
    public ResponseEntity<Object> getPost(@PathVariable(value = "post_id") Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }
}
