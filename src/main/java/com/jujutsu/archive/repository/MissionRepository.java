package com.jujutsu.archive.repository;

import com.jujutsu.archive.model.entity.MissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с сущностью MissionEntity.
 * Spring Data JPA автоматически реализует все методы интерфейса.
 */
@Repository
public interface MissionRepository extends JpaRepository<MissionEntity, String> {

    /**
     * Найти миссию по уникальному идентификатору
     */
    Optional<MissionEntity> findByMissionId(String missionId);

    /**
     * Проверить существование миссии по идентификатору
     */
    boolean existsByMissionId(String missionId);

    /**
     * Найти все миссии по месту проведения (без учёта регистра)
     */
    List<MissionEntity> findByLocationContainingIgnoreCase(String location);

    /**
     * Найти все миссии за указанный период
     */
    List<MissionEntity> findByDateBetween(LocalDate from, LocalDate to);

    /**
     * Получить все миссии, отсортированные по дате (новые первыми)
     */
    List<MissionEntity> findAllByOrderByDateDesc();
}