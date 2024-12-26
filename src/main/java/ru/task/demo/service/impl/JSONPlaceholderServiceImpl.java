package ru.task.demo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.task.demo.exception.RequestException;
import ru.task.demo.service.JSONPlaceholderService;
import ru.task.demo.service.dto.post.CreatePostDto;
import ru.task.demo.service.dto.post.GetPostFromJSONPlaceholderResponse;
import ru.task.demo.service.dto.post.SimplePostDto;


@Service
@RequiredArgsConstructor
@Slf4j
public class JSONPlaceholderServiceImpl implements JSONPlaceholderService {
    //YAGNI. На замену RestTemplate пришел WebClient, но для этого надо подключать доп библиотеку/стартер
    //Он нам не понадобится, хватит и RestTemplate
    //Конфигурацию так же можно опустить, сервис JSONPlaceholder сильно упрощен и не требует например авторизацию
    RestTemplate restTemplate = new RestTemplate();

    @Value("${custom.JSONPlaceholder.url}")
    private String placeholderUrl;
    @Value("${custom.JSONPlaceholder.maxRetry}")
    private int maxRetry;


    @Override
    public GetPostFromJSONPlaceholderResponse getPost(final Long postId) {

        ResponseEntity<GetPostFromJSONPlaceholderResponse> response = exchangeFromJSONPlaceholder(
            "/posts/" + postId,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            GetPostFromJSONPlaceholderResponse.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            //тут стоит более детально поработать с провайдером и выяснить
            //какие ошибки он может кидать и в каких случаях
            throw new RequestException("ошибка взаимодействия с провайдером, код " + response.getStatusCode().value());
        }
        return response.getBody();
    }

    @Override
    public GetPostFromJSONPlaceholderResponse createPost(final SimplePostDto createPostRequest) {
        CreatePostDto requestBody = CreatePostDto.builder()
            //константа, базы пользователя с провайдером не согласованы в рамках тестового
            .userId(1L)
            .body(createPostRequest.getBody())
            .title(createPostRequest.getTitle())
            .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<CreatePostDto> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<GetPostFromJSONPlaceholderResponse> response = exchangeFromJSONPlaceholder(
            "/posts",
            HttpMethod.POST,
            request,
            GetPostFromJSONPlaceholderResponse.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            //тут стоит более детально поработать с провайдером и выяснить
            //какие ошибки он может кидать и в каких случаях
            throw new RequestException("ошибка взаимодействия с провайдером, код " + response.getStatusCode().value());
        }
        return response.getBody();
    }

    //можно вынести в отдельный класс и использовать для запросов в другие сервисы
    //не стал подключать сторонние библиотеки для сложного контроля ретраев, сделал руками
    private <T> ResponseEntity<T> exchangeFromJSONPlaceholder(final String path,
                                                              final HttpMethod httpMethod,
                                                              final HttpEntity<?> request,
                                                              final Class<T> responseType) {
        ResponseEntity<T> response;
        int countRetry = 0;
        while (countRetry < maxRetry) {
            try {
                response = restTemplate
                    .exchange(placeholderUrl + path, httpMethod, request, responseType);


                if (!response.getStatusCode().is5xxServerError()) {
                    return response;
                }
                countRetry++;
            } catch (RestClientException ex) {
                countRetry++;
            }
        }
        throw new InternalError("Ошибка при взаимодействии с провайдером");
    }
}
