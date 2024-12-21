package ru.task.demo.repositories.section;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.task.demo.entity.Section;

import java.util.List;
import java.util.UUID;

/**
 * Компонент раздела
 */
public interface SectionComponent {
    /**
     * Получить разделы или ошибка
     * @param idSections идентификатор раздела
     * @return список разделов
     */
    List<Section> getSectionsOrDie(List<UUID> idSections);
    /**
     * Получить все разделы с пагинацией
     * @param pageRequest пагинация
     * @return страница разделов
     */
    Page<Section> getAllSections(PageRequest pageRequest);

    /**
     * Сохранить раздел в БД
     * @param section раздел
     */
    Section save(Section section);

    /**
     * Удалить раздел из БД если существует, иначе ошибка
     * @param sectionId идентификатор раздела
     */
    void deleteOrDie(UUID sectionId);
}
