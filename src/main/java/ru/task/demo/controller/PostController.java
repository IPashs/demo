package ru.task.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.task.demo.service.PostService;
import ru.task.demo.service.dto.post.SimplePostDto;

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

    @PostMapping("/posts")
    public ResponseEntity<Object> createProject(
        @RequestBody @Valid SimplePostDto createPostRequest) {
        return new ResponseEntity<>(postService.createPost(createPostRequest), HttpStatus.CREATED);
    }

}
