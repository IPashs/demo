package ru.task.demo.repositories.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostComponentImpl implements PostComponent {
    private final PostRepository postRepository;
}
