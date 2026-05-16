package com.jujutsu.archive.model.entity;

import com.jujutsu.archive.enums.Outcome;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Главная JPA сущность миссии — корень агрегата.
 * Содержит все связанные объекты через @OneToOne и @OneToMany.
 */
@Entity
@Table(name = "missions")
public class MissionEntity {

    // Уникальный идентификатор миссии (бизнес-ключ, задаётся вручную)
    @Id
    @Column(name = "mission_id", nullable = false, unique = true)
    private String missionId;

    // Дата проведения миссии — обязательное поле
    @Column(name = "date", nullable = false)
    private LocalDate date;

    // Место проведения — обязательное поле
    @Column(name = "location", nullable = false)
    private String location;

    // Исход миссии — обязательное поле, хранится как строка
    @Enumerated(EnumType.STRING)
    @Column(name = "outcome", nullable = false)
    private Outcome outcome;

    // Общий ущерб от миссии — необязательное поле
    @Column(name = "damage_cost")
    private Double damageCost;

    // Дополнительные заметки
    @Column(name = "notes", length = 2000)
    private String notes;

    // ---- Списки строк через @ElementCollection ----

    // Теги операции
    @ElementCollection
    @CollectionTable(
            name = "mission_operation_tags",
            joinColumns = @JoinColumn(name = "mission_id")
    )
    @Column(name = "tag")
    private List<String> operationTags;

    // Задействованные вспомогательные подразделения
    @ElementCollection
    @CollectionTable(
            name = "mission_support_units",
            joinColumns = @JoinColumn(name = "mission_id")
    )
    @Column(name = "unit")
    private List<String> supportUnits;

    // Рекомендации по итогам миссии
    @ElementCollection
    @CollectionTable(
            name = "mission_recommendations",
            joinColumns = @JoinColumn(name = "mission_id")
    )
    @Column(name = "recommendation")
    private List<String> recommendations;

    // Обнаруженные артефакты
    @ElementCollection
    @CollectionTable(
            name = "mission_artifacts_recovered",
            joinColumns = @JoinColumn(name = "mission_id")
    )
    @Column(name = "artifact")
    private List<String> artifactsRecovered;

    // Зоны эвакуации
    @ElementCollection
    @CollectionTable(
            name = "mission_evacuation_zones",
            joinColumns = @JoinColumn(name = "mission_id")
    )
    @Column(name = "zone")
    private List<String> evacuationZones;

    // Статусные эффекты
    @ElementCollection
    @CollectionTable(
            name = "mission_status_effects",
            joinColumns = @JoinColumn(name = "mission_id")
    )
    @Column(name = "effect")
    private List<String> statusEffects;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "curse_id")
    private CurseEntity curse;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "economic_assessment_id")
    private EconomicAssessmentEntity economicAssessment;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "civilian_impact_id")
    private CivilianImpactEntity civilianImpact;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "enemy_activity_id")
    private EnemyActivityEntity enemyActivity;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "environment_conditions_id")
    private EnvironmentConditionsEntity environmentConditions;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SorcererEntity> sorcerers;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TechniqueEntity> techniques;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OperationTimelineEntity> operationTimeline;

    public MissionEntity() {}


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

    public CurseEntity getCurse() { return curse; }
    public void setCurse(CurseEntity curse) { this.curse = curse; }

    public EconomicAssessmentEntity getEconomicAssessment() { return economicAssessment; }
    public void setEconomicAssessment(EconomicAssessmentEntity economicAssessment) { this.economicAssessment = economicAssessment; }

    public CivilianImpactEntity getCivilianImpact() { return civilianImpact; }
    public void setCivilianImpact(CivilianImpactEntity civilianImpact) { this.civilianImpact = civilianImpact; }

    public EnemyActivityEntity getEnemyActivity() { return enemyActivity; }
    public void setEnemyActivity(EnemyActivityEntity enemyActivity) { this.enemyActivity = enemyActivity; }

    public EnvironmentConditionsEntity getEnvironmentConditions() { return environmentConditions; }
    public void setEnvironmentConditions(EnvironmentConditionsEntity environmentConditions) { this.environmentConditions = environmentConditions; }

    public List<SorcererEntity> getSorcerers() { return sorcerers; }
    public void setSorcerers(List<SorcererEntity> sorcerers) { this.sorcerers = sorcerers; }

    public List<TechniqueEntity> getTechniques() { return techniques; }
    public void setTechniques(List<TechniqueEntity> techniques) { this.techniques = techniques; }

    public List<OperationTimelineEntity> getOperationTimeline() { return operationTimeline; }
    public void setOperationTimeline(List<OperationTimelineEntity> operationTimeline) { this.operationTimeline = operationTimeline; }
}