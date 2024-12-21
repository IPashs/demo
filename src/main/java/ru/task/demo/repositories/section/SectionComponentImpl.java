package ru.task.demo.repositories.section;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.task.demo.entity.Section;
import ru.task.demo.exception.NoSuchElementException;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SectionComponentImpl implements SectionComponent {
    private final SectionRepository sectionRepository;

    //Не ясно что делать если 1 или несколько из разделов не будут найдены.
    //Можно пройтись в цикле по каждому переданному разделу и бросить исключение
    //Если хотя бы 1 не найден. Получится несколько запросов, однако не будет критичным
    //Учитывая специфику (у проекта не может быть слишком много разделов)
    @Override
    public List<Section> getSectionsOrDie(final List<UUID> idSections) {
        List<Section> sections = sectionRepository.findAllById(idSections);
        if (sections.isEmpty()) {
            throw new NoSuchElementException("ни один раздел не найден");
        }
        return sections;
    }

    @Override
    public Page<Section> getAllSections(final PageRequest pageRequest) {
        return sectionRepository.findAll(pageRequest);
    }

    @Override
    public Section save(final Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void deleteOrDie(final UUID sectionId) {
       Section section = sectionRepository.findById(sectionId)
           .orElseThrow(()-> new NoSuchElementException("Раздел не найден"));
       sectionRepository.delete(section);
    }
}
