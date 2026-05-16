package com.jujutsu.archive.service.parser;

import com.jujutsu.archive.model.dto.MissionDto;

import java.io.InputStream;


public interface MissionParser {

    MissionDto parse(InputStream inputStream) throws Exception;
}