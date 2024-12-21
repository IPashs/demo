package ru.task.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.task.demo.service.dto.SimpleIdNameDto;

import java.util.UUID;

/**
 * Сервис разделов
 */
public interface SectionService {
    /**
     * Получить страницу с разделами
     * @param pageRequest страничный запрос на получение разделов
     * @return страница с разделами
     */
    Page<SimpleIdNameDto> getSections(PageRequest pageRequest);

    /**
     * Создать раздел
     * @param sectionName название раздела
     */
    void createSection(String sectionName);

    /**
     * Удалить раздел по id
     * @param sectionId идентификатор раздела
     */
    void deleteSection(UUID sectionId);
}
