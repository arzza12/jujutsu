package com.jujutsu.archive.service;

import com.jujutsu.archive.mapper.MissionMapper;
import com.jujutsu.archive.model.dto.MissionDto;
import com.jujutsu.archive.model.entity.MissionEntity;
import com.jujutsu.archive.repository.MissionRepository;
import com.jujutsu.archive.service.parser.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionMapper missionMapper;

    private final JsonMissionParser jsonParser;
    private final XmlMissionParser xmlParser;
    private final YamlMissionParser yamlParser;
    private final TextMissionParser textParser;
    private final PipeMissionParser pipeParser;

    public MissionService(MissionRepository missionRepository,
                          MissionMapper missionMapper,
                          JsonMissionParser jsonParser,
                          XmlMissionParser xmlParser,
                          YamlMissionParser yamlParser,
                          TextMissionParser textParser,
                          PipeMissionParser pipeParser) {
        this.missionRepository = missionRepository;
        this.missionMapper = missionMapper;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
        this.yamlParser = yamlParser;
        this.textParser = textParser;
        this.pipeParser = pipeParser;
    }


    public MissionDto saveMission(MultipartFile file, String format) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Файл не может быть пустым");
        }

        if (format == null || format.isBlank()) {
            throw new IllegalArgumentException("Формат файла не указан");
        }

        MissionDto dto;
        try {
            dto = selectParser(format).parse(file.getInputStream());
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Ошибка при разборе файла формата [" + format + "]: " + e.getMessage(), e
            );
        }

        validateDto(dto);

        if (missionRepository.existsByMissionId(dto.getMissionId())) {
            throw new IllegalArgumentException(
                    "Миссия с идентификатором [" + dto.getMissionId() + "] уже существует в архиве"
            );
        }

        MissionEntity entity = missionMapper.toEntity(dto);
        MissionEntity saved = missionRepository.save(entity);

        return missionMapper.toDto(saved);
    }

    /**
     * Получить список всех миссий, отсортированных по дате (новые первыми).
     */
    public List<MissionDto> getAllMissions() {
        return missionRepository.findAllByOrderByDateDesc()
                .stream()
                .map(missionMapper::toDto)
                .collect(Collectors.toList());
    }

    public MissionDto getMissionById(String missionId) {
        MissionEntity entity = missionRepository.findByMissionId(missionId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Миссия с идентификатором [" + missionId + "] не найдена"
                ));
        return missionMapper.toDto(entity);
    }


    private MissionParser selectParser(String format) {
        return switch (format.toLowerCase().trim()) {
            case "json"        -> jsonParser;
            case "xml"         -> xmlParser;
            case "yaml", "yml" -> yamlParser;
            case "text", "txt" -> textParser;
            case "pipe", "log" -> pipeParser;
            default -> throw new IllegalArgumentException(
                    "Неизвестный формат файла: [" + format + "]. " +
                            "Поддерживаемые форматы: json, xml, yaml, text, pipe"
            );
        };
    }

    /**
     * Валидация обязательных полей DTO миссии.
     */
    private void validateDto(MissionDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Не удалось распарсить данные миссии");
        }
        if (dto.getMissionId() == null || dto.getMissionId().isBlank()) {
            throw new IllegalArgumentException("Обязательное поле отсутствует: missionId");
        }
        if (dto.getDate() == null) {
            throw new IllegalArgumentException("Обязательное поле отсутствует: date");
        }
        if (dto.getLocation() == null || dto.getLocation().isBlank()) {
            throw new IllegalArgumentException("Обязательное поле отсутствует: location");
        }
        if (dto.getOutcome() == null) {
            throw new IllegalArgumentException("Обязательное поле отсутствует: outcome");
        }
        if (dto.getCurse() == null) {
            throw new IllegalArgumentException("Обязательное поле отсутствует: curse");
        }
        if (dto.getCurse().getName() == null || dto.getCurse().getName().isBlank()) {
            throw new IllegalArgumentException("Обязательное поле отсутствует: curse.name");
        }
    }
}