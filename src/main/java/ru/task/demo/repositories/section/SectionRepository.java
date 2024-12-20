package ru.task.demo.repositories.section;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.task.demo.entity.Section;

import java.util.UUID;

/**
 * Репозиторий раздела
 */
@Repository
interface SectionRepository extends JpaRepository<Section, UUID> {
}
