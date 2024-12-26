package ru.task.demo.repositories.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.task.demo.entity.Post;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostComponentImpl implements PostComponent {
    private final PostRepository postRepository;

    @Override
    public Optional<Post> findByApiId(final Long postId) {
        return postRepository.findByApiId(postId);
    }

    @Override
    public Post save(final Post post) {
        return postRepository.save(post);
    }
}
