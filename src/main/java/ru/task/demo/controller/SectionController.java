package ru.task.demo.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.task.demo.service.SectionService;
import ru.task.demo.service.dto.SearchPage;

import java.util.UUID;

/**
 * Контроллер с методами для разделов проекта
 */
@RestController
@RequiredArgsConstructor
@RequestMapping
public class SectionController {
    private final SectionService sectionService;

    @GetMapping("/sections")
    public ResponseEntity<Object> getAllSections(
        @RequestParam(value = "pageNumber", required = false, defaultValue = "0") final Integer pageNumber,
        @RequestParam(value = "pageSize", required = false, defaultValue = "100") final Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        return ResponseEntity.ok(SearchPage.fromPage(sectionService.getSections(pageRequest)));
    }

    @PostMapping("/sections")
    public ResponseEntity<Object> createSection(
        @RequestParam(value = "name") final String name) {
        sectionService.createSection(name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/sections/{section_id}")
    public ResponseEntity<Object> deleteSection(
        @PathVariable(value = "section_id") @NotNull UUID sectionId) {
        sectionService.deleteSection(sectionId);
        return ResponseEntity.ok().build();
    }
}
