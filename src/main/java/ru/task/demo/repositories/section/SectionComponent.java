package ru.task.demo.repositories.section;

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
}
