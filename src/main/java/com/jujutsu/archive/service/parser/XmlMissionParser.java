package com.jujutsu.archive.service.parser;

import com.jujutsu.archive.model.dto.MissionDto;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;

import java.io.InputStream;


@Component("xmlParser")
public class XmlMissionParser implements MissionParser {


    @Override
    public MissionDto parse(InputStream inputStream) throws Exception {
        // Создаём контекст JAXB для класса MissionDto
        JAXBContext context = JAXBContext.newInstance(MissionDto.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (MissionDto) unmarshaller.unmarshal(inputStream);
    }
}