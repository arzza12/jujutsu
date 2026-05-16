package com.jujutsu.archive.model.dto;

import com.jujutsu.archive.enums.ThreatLevel;

/**
 * DTO проклятого духа (Curse)
 */
public class CurseDto {

    // Имя проклятия
    private String name;

    // Уровень угрозы
    private ThreatLevel threatLevel;

    public CurseDto() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public ThreatLevel getThreatLevel() { return threatLevel; }
    public void setThreatLevel(ThreatLevel threatLevel) { this.threatLevel = threatLevel; }
}