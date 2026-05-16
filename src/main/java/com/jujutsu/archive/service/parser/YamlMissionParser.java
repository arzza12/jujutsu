package com.jujutsu.archive.service.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jujutsu.archive.model.dto.MissionDto;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;


@Component("yamlParser")
public class YamlMissionParser implements MissionParser {

    private final ObjectMapper objectMapper;

    private final Yaml yaml;

    public YamlMissionParser() {
        this.yaml = new Yaml();

        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.objectMapper.configure(
                com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false
        );
    }


    @Override
    public MissionDto parse(InputStream inputStream) throws Exception {
        Map<String, Object> yamlMap = yaml.load(inputStream);

        return objectMapper.convertValue(yamlMap, MissionDto.class);
    }
}