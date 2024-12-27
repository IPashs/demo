package ru.task.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.task.demo.entity.Post;
import ru.task.demo.repositories.post.PostComponent;
import ru.task.demo.service.JSONPlaceholderService;
import ru.task.demo.service.PostService;
import ru.task.demo.service.UserService;
import ru.task.demo.service.dto.post.GetPostFromJSONPlaceholderResponse;
import ru.task.demo.service.dto.post.GetPostResponse;
import ru.task.demo.service.dto.post.SimplePostDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostComponent postComponent;
    private final JSONPlaceholderService jsonPlaceholderService;
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
            post = this.savePost(response);
        }
        return this.createGetPostResponse(post);
    }

    @Override
    public GetPostResponse createPost(final SimplePostDto createPostRequest) {
        /*
        Тут мы сталкиваемся с проблемой распределенной транзакции
        если у провайдера пост успешно создался, а у нас нет, мы должны запустить откатную транзакцию
        и удалить пост у провайдера, но этого тоже может не произойти да и вообще может произойти что угодно
        даже с сервером, где запущен процесс. Реализация функционала защиты от такой ситуации не входит в рамки
        тестового задания, но по-хорошему тут надо сохранять наши операции перед тем как мы их выполним и
        писать в логи перед тем как сохранить операцию в бд, так создадим себе почву
        для обработки таких событий и в будущем сможем реализовать гарантию корректного выполнения таких операций
         */
        //так же мы никак не проверяем существует ли уже такой пост*
        GetPostFromJSONPlaceholderResponse createdPost = jsonPlaceholderService.createPost(createPostRequest);

        Optional<Post> postOpt = postComponent.findByApiId(createdPost.getId());
        Post post = postOpt.orElseGet(() -> this.savePost(createdPost));

        return this.createGetPostResponse(post);
    }

    private GetPostResponse createGetPostResponse(Post post) {
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
