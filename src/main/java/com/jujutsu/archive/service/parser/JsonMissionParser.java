package com.jujutsu.archive.service.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jujutsu.archive.model.dto.MissionDto;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component("jsonParser")
public class JsonMissionParser implements MissionParser {


    private final ObjectMapper objectMapper;

    public JsonMissionParser() {
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
        return objectMapper.readValue(inputStream, MissionDto.class);
    }
}