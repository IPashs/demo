package ru.task.demo.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.task.demo.entity.Project;
import ru.task.demo.repositories.project.ProjectComponent;

import java.nio.charset.StandardCharsets;

class ProjectControllerTest extends BaseTest {

    @Autowired
    private ProjectComponent projectComponent;

    @Test
    @WithMockUser(username = "mail3@mail.ru")
    void createProject_atNormalCase() throws Exception {

        this.mockMvc
            .perform(MockMvcRequestBuilders.post("/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(readFileAsString("json/createProjectRequest.json", StandardCharsets.UTF_8)))
            .andExpect(MockMvcResultMatchers.status().isCreated());

        PageRequest pageRequest = PageRequest.of(0, 100, Sort.by("name"));

        Project project = projectComponent.getAllProjects(pageRequest)
            .stream()
            .findFirst()
            .orElseThrow();
        Project projectWithDetails = projectComponent.getProjectWithSectionAndAuthorOrDie(project.getId());
        Assertions.assertEquals("название проекта", project.getName());
        Assertions.assertEquals("mail3@mail.ru", projectWithDetails.getAuthor().getEmail());
    }
}