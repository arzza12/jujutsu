package com.jujutsu.archive.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.jujutsu.archive.enums.Outcome;

import jakarta.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Главный DTO миссии — используется для парсинга JSON/XML/YAML/TXT/PIPE
 * и передачи данных между слоями приложения.
 */
@XmlRootElement(name = "mission")
@XmlAccessorType(XmlAccessType.FIELD)
public class MissionDto {

    private String missionId;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String location;
    private Outcome outcome;
    private Double damageCost;
    private String notes;

    @XmlElementWrapper(name = "operationTags")
    @XmlElement(name = "tag")
    private List<String> operationTags;

    @XmlElementWrapper(name = "supportUnits")
    @XmlElement(name = "unit")
    private List<String> supportUnits;

    @XmlElementWrapper(name = "recommendations")
    @XmlElement(name = "recommendation")
    private List<String> recommendations;

    @XmlElementWrapper(name = "artifactsRecovered")
    @XmlElement(name = "artifact")
    private List<String> artifactsRecovered;

    @XmlElementWrapper(name = "evacuationZones")
    @XmlElement(name = "zone")
    private List<String> evacuationZones;

    @XmlElementWrapper(name = "statusEffects")
    @XmlElement(name = "effect")
    private List<String> statusEffects;

    // ---- Связанные объекты ----
    private CurseDto curse;

    @XmlElementWrapper(name = "sorcerers")
    @XmlElement(name = "sorcerer")
    private List<SorcererDto> sorcerers;

    @XmlElementWrapper(name = "techniques")
    @XmlElement(name = "technique")
    private List<TechniqueDto> techniques;

    private EconomicAssessmentDto economicAssessment;
    private CivilianImpactDto civilianImpact;
    private EnemyActivityDto enemyActivity;
    private EnvironmentConditionsDto environmentConditions;

    @XmlElementWrapper(name = "operationTimeline")
    @XmlElement(name = "event")
    private List<OperationTimelineDto> operationTimeline;

    public MissionDto() {}

    public String getMissionId() { return missionId; }
    public void setMissionId(String missionId) { this.missionId = missionId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Outcome getOutcome() { return outcome; }
    public void setOutcome(Outcome outcome) { this.outcome = outcome; }

    public Double getDamageCost() { return damageCost; }
    public void setDamageCost(Double damageCost) { this.damageCost = damageCost; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public List<String> getOperationTags() { return operationTags; }
    public void setOperationTags(List<String> operationTags) { this.operationTags = operationTags; }

    public List<String> getSupportUnits() { return supportUnits; }
    public void setSupportUnits(List<String> supportUnits) { this.supportUnits = supportUnits; }

    public List<String> getRecommendations() { return recommendations; }
    public void setRecommendations(List<String> recommendations) { this.recommendations = recommendations; }

    public List<String> getArtifactsRecovered() { return artifactsRecovered; }
    public void setArtifactsRecovered(List<String> artifactsRecovered) { this.artifactsRecovered = artifactsRecovered; }

    public List<String> getEvacuationZones() { return evacuationZones; }
    public void setEvacuationZones(List<String> evacuationZones) { this.evacuationZones = evacuationZones; }

    public List<String> getStatusEffects() { return statusEffects; }
    public void setStatusEffects(List<String> statusEffects) { this.statusEffects = statusEffects; }

    public CurseDto getCurse() { return curse; }
    public void setCurse(CurseDto curse) { this.curse = curse; }

    public List<SorcererDto> getSorcerers() { return sorcerers; }
    public void setSorcerers(List<SorcererDto> sorcerers) { this.sorcerers = sorcerers; }

    public List<TechniqueDto> getTechniques() { return techniques; }
    public void setTechniques(List<TechniqueDto> techniques) { this.techniques = techniques; }

    public EconomicAssessmentDto getEconomicAssessment() { return economicAssessment; }
    public void setEconomicAssessment(EconomicAssessmentDto economicAssessment) { this.economicAssessment = economicAssessment; }

    public CivilianImpactDto getCivilianImpact() { return civilianImpact; }
    public void setCivilianImpact(CivilianImpactDto civilianImpact) { this.civilianImpact = civilianImpact; }

    public EnemyActivityDto getEnemyActivity() { return enemyActivity; }
    public void setEnemyActivity(EnemyActivityDto enemyActivity) { this.enemyActivity = enemyActivity; }

    public EnvironmentConditionsDto getEnvironmentConditions() { return environmentConditions; }
    public void setEnvironmentConditions(EnvironmentConditionsDto environmentConditions) { this.environmentConditions = environmentConditions; }

    public List<OperationTimelineDto> getOperationTimeline() { return operationTimeline; }
    public void setOperationTimeline(List<OperationTimelineDto> operationTimeline) { this.operationTimeline = operationTimeline; }
}