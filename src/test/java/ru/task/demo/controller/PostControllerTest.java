package ru.task.demo.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.task.demo.service.JSONPlaceholderService;
import ru.task.demo.service.dto.post.GetPostFromJSONPlaceholderResponse;

import java.nio.charset.StandardCharsets;

class PostControllerTest extends BaseTest {

    @MockitoBean
    private JSONPlaceholderService jsonPlaceholderService;

    @Test
    @WithMockUser(username = "mail3@mail.ru")
    void getPost_atNormalCase() throws Exception {
        Mockito.when(jsonPlaceholderService.getPost(ArgumentMatchers.anyLong()))
            .thenReturn(GetPostFromJSONPlaceholderResponse.builder()
                .id(1L)
                .title("title")
                .body("body")
                .userId(1L)
                .build());
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/posts/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("body"));
    }

    @Test
    @WithMockUser(username = "mail3@mail.ru")
    void createProject_atNormalCase() throws Exception {
        Mockito.when(jsonPlaceholderService.createPost(ArgumentMatchers.any()))
            .thenReturn(GetPostFromJSONPlaceholderResponse.builder()
                .id(1L)
                .title("title")
                .body("body")
                .userId(1L)
                .build());
        this.mockMvc
            .perform(MockMvcRequestBuilders.post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(readFileAsString("json/createPostRequest.json", StandardCharsets.UTF_8)))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("body"));
    }
}