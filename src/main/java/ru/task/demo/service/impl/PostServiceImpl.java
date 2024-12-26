package ru.task.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task.demo.entity.Post;
import ru.task.demo.repositories.post.PostComponent;
import ru.task.demo.service.PostService;
import ru.task.demo.service.UserService;
import ru.task.demo.service.dto.post.GetPostFromJSONPlaceholderResponse;
import ru.task.demo.service.dto.post.GetPostResponse;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostComponent postComponent;
    private final JSONPlaceholderServiceImpl jsonPlaceholderService;
    private final UserService userService;

    @Override
    public GetPostResponse getPost(final Long postId) {
        Post post;
        //Если пост есть в нашей бд - нет смысла ходить к провайдеру
        Optional<Post> postOpt = postComponent.findByApiId(postId);
        if (postOpt.isPresent()) {
            post = postOpt.get();
        } else {
            //если в БД нет поста - ищем его у провайдера и сохраняем себе, потом отдаем пользователю
            //если и у провайдера нет поста, пользователь получит ошибку 400
            GetPostFromJSONPlaceholderResponse response = jsonPlaceholderService.getPost(postId);
            post = savePost(response);
        }
        return GetPostResponse.builder()
            .postId(post.getId())
            .title(post.getTitle())
            .body(post.getBody())
            .userId(post.getUser().getId())
            .build();
    }

    private Post savePost(final GetPostFromJSONPlaceholderResponse dto) {
        Post post = Post.builder()
            .apiId(dto.getId())
            .title(dto.getTitle())
            .body(dto.getBody())
            .user(userService.getCurrentUser())
            .build();
        return postComponent.save(post);
    }
}
