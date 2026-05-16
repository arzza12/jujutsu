package com.jujutsu.archive.mapper;

import com.jujutsu.archive.model.dto.*;
import com.jujutsu.archive.model.entity.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Маппер для преобразования между MissionDto и MissionEntity.
 * Все вложенные объекты маппируются рекурсивно.
 */
@Component
public class MissionMapper {

    // ==========================================
    // DTO → Entity
    // ==========================================

    /**
     * Преобразовать MissionDto в MissionEntity
     */
    public MissionEntity toEntity(MissionDto dto) {
        if (dto == null) return null;

        MissionEntity entity = new MissionEntity();
        entity.setMissionId(dto.getMissionId());
        entity.setDate(dto.getDate());
        entity.setLocation(dto.getLocation());
        entity.setOutcome(dto.getOutcome());
        entity.setDamageCost(dto.getDamageCost());
        entity.setNotes(dto.getNotes());
        entity.setOperationTags(dto.getOperationTags());
        entity.setSupportUnits(dto.getSupportUnits());
        entity.setRecommendations(dto.getRecommendations());
        entity.setArtifactsRecovered(dto.getArtifactsRecovered());
        entity.setEvacuationZones(dto.getEvacuationZones());
        entity.setStatusEffects(dto.getStatusEffects());

        // Маппинг проклятия (обязательный)
        entity.setCurse(toCurseEntity(dto.getCurse()));

        // Маппинг необязательных 1:1 объектов
        entity.setEconomicAssessment(toEconomicAssessmentEntity(dto.getEconomicAssessment()));
        entity.setCivilianImpact(toCivilianImpactEntity(dto.getCivilianImpact()));
        entity.setEnemyActivity(toEnemyActivityEntity(dto.getEnemyActivity()));
        entity.setEnvironmentConditions(toEnvironmentConditionsEntity(dto.getEnvironmentConditions()));

        // Маппинг списков 1:N
        if (dto.getSorcerers() != null) {
            List<SorcererEntity> sorcerers = dto.getSorcerers().stream()
                    .map(s -> {
                        SorcererEntity se = toSorcererEntity(s);
                        se.setMission(entity); // устанавливаем обратную связь
                        return se;
                    })
                    .collect(Collectors.toList());
            entity.setSorcerers(sorcerers);
        } else {
            entity.setSorcerers(Collections.emptyList());
        }

        if (dto.getTechniques() != null) {
            List<TechniqueEntity> techniques = dto.getTechniques().stream()
                    .map(t -> {
                        TechniqueEntity te = toTechniqueEntity(t);
                        te.setMission(entity); // устанавливаем обратную связь
                        return te;
                    })
                    .collect(Collectors.toList());
            entity.setTechniques(techniques);
        } else {
            entity.setTechniques(Collections.emptyList());
        }

        if (dto.getOperationTimeline() != null) {
            List<OperationTimelineEntity> timeline = dto.getOperationTimeline().stream()
                    .map(t -> {
                        OperationTimelineEntity te = toTimelineEntity(t);
                        te.setMission(entity); // устанавливаем обратную связь
                        return te;
                    })
                    .collect(Collectors.toList());
            entity.setOperationTimeline(timeline);
        } else {
            entity.setOperationTimeline(Collections.emptyList());
        }

        return entity;
    }

    /**
     * Маппинг CurseDto → CurseEntity
     */
    private CurseEntity toCurseEntity(CurseDto dto) {
        if (dto == null) return null;
        CurseEntity entity = new CurseEntity();
        entity.setName(dto.getName());
        entity.setThreatLevel(dto.getThreatLevel());
        return entity;
    }

    /**
     * Маппинг SorcererDto → SorcererEntity
     */
    private SorcererEntity toSorcererEntity(SorcererDto dto) {
        if (dto == null) return null;
        SorcererEntity entity = new SorcererEntity();
        entity.setName(dto.getName());
        entity.setRank(dto.getRank());
        return entity;
    }

    /**
     * Маппинг TechniqueDto → TechniqueEntity
     */
    private TechniqueEntity toTechniqueEntity(TechniqueDto dto) {
        if (dto == null) return null;
        TechniqueEntity entity = new TechniqueEntity();
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setOwner(dto.getOwner());
        entity.setDamage(dto.getDamage());
        return entity;
    }

    /**
     * Маппинг EconomicAssessmentDto → EconomicAssessmentEntity
     */
    private EconomicAssessmentEntity toEconomicAssessmentEntity(EconomicAssessmentDto dto) {
        if (dto == null) return null;
        EconomicAssessmentEntity entity = new EconomicAssessmentEntity();
        entity.setTotalDamageCost(dto.getTotalDamageCost());
        entity.setInfrastructureDamage(dto.getInfrastructureDamage());
        entity.setCommercialDamage(dto.getCommercialDamage());
        entity.setTransportDamage(dto.getTransportDamage());
        entity.setRecoveryEstimateDays(dto.getRecoveryEstimateDays());
        entity.setInsuranceCovered(dto.getInsuranceCovered());
        return entity;
    }

    /**
     * Маппинг CivilianImpactDto → CivilianImpactEntity
     */
    private CivilianImpactEntity toCivilianImpactEntity(CivilianImpactDto dto) {
        if (dto == null) return null;
        CivilianImpactEntity entity = new CivilianImpactEntity();
        entity.setEvacuated(dto.getEvacuated());
        entity.setInjured(dto.getInjured());
        entity.setMissing(dto.getMissing());
        entity.setPublicExposureRisk(dto.getPublicExposureRisk());
        return entity;
    }

    /**
     * Маппинг EnemyActivityDto → EnemyActivityEntity
     */
    private EnemyActivityEntity toEnemyActivityEntity(EnemyActivityDto dto) {
        if (dto == null) return null;
        EnemyActivityEntity entity = new EnemyActivityEntity();
        entity.setBehaviorType(dto.getBehaviorType());
        entity.setTargetPriority(dto.getTargetPriority());
        entity.setAttackPatterns(dto.getAttackPatterns());
        entity.setMobility(dto.getMobility());
        entity.setEscalationRisk(dto.getEscalationRisk());
        entity.setCountermeasuresUsed(dto.getCountermeasuresUsed());
        return entity;
    }

    /**
     * Маппинг EnvironmentConditionsDto → EnvironmentConditionsEntity
     */
    private EnvironmentConditionsEntity toEnvironmentConditionsEntity(EnvironmentConditionsDto dto) {
        if (dto == null) return null;
        EnvironmentConditionsEntity entity = new EnvironmentConditionsEntity();
        entity.setWeather(dto.getWeather());
        entity.setTimeOfDay(dto.getTimeOfDay());
        entity.setVisibility(dto.getVisibility());
        entity.setCursedEnergyDensity(dto.getCursedEnergyDensity());
        return entity;
    }

    /**
     * Маппинг OperationTimelineDto → OperationTimelineEntity
     */
    private OperationTimelineEntity toTimelineEntity(OperationTimelineDto dto) {
        if (dto == null) return null;
        OperationTimelineEntity entity = new OperationTimelineEntity();
        entity.setTimestamp(dto.getTimestamp());
        entity.setType(dto.getType());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    // ==========================================
    // Entity → DTO
    // ==========================================

    /**
     * Преобразовать MissionEntity в MissionDto
     */
    public MissionDto toDto(MissionEntity entity) {
        if (entity == null) return null;

        MissionDto dto = new MissionDto();
        dto.setMissionId(entity.getMissionId());
        dto.setDate(entity.getDate());
        dto.setLocation(entity.getLocation());
        dto.setOutcome(entity.getOutcome());
        dto.setDamageCost(entity.getDamageCost());
        dto.setNotes(entity.getNotes());
        dto.setOperationTags(entity.getOperationTags());
        dto.setSupportUnits(entity.getSupportUnits());
        dto.setRecommendations(entity.getRecommendations());
        dto.setArtifactsRecovered(entity.getArtifactsRecovered());
        dto.setEvacuationZones(entity.getEvacuationZones());
        dto.setStatusEffects(entity.getStatusEffects());

        // Маппинг проклятия
        dto.setCurse(toCurseDto(entity.getCurse()));

        // Маппинг необязательных 1:1 объектов
        dto.setEconomicAssessment(toEconomicAssessmentDto(entity.getEconomicAssessment()));
        dto.setCivilianImpact(toCivilianImpactDto(entity.getCivilianImpact()));
        dto.setEnemyActivity(toEnemyActivityDto(entity.getEnemyActivity()));
        dto.setEnvironmentConditions(toEnvironmentConditionsDto(entity.getEnvironmentConditions()));

        // Маппинг списков 1:N
        if (entity.getSorcerers() != null) {
            dto.setSorcerers(entity.getSorcerers().stream()
                    .map(this::toSorcererDto)
                    .collect(Collectors.toList()));
        }

        if (entity.getTechniques() != null) {
            dto.setTechniques(entity.getTechniques().stream()
                    .map(this::toTechniqueDto)
                    .collect(Collectors.toList()));
        }

        if (entity.getOperationTimeline() != null) {
            dto.setOperationTimeline(entity.getOperationTimeline().stream()
                    .map(this::toTimelineDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    /**
     * Маппинг CurseEntity → CurseDto
     */
    private CurseDto toCurseDto(CurseEntity entity) {
        if (entity == null) return null;
        CurseDto dto = new CurseDto();
        dto.setName(entity.getName());
        dto.setThreatLevel(entity.getThreatLevel());
        return dto;
    }

    /**
     * Маппинг SorcererEntity → SorcererDto
     */
    private SorcererDto toSorcererDto(SorcererEntity entity) {
        if (entity == null) return null;
        SorcererDto dto = new SorcererDto();
        dto.setName(entity.getName());
        dto.setRank(entity.getRank());
        return dto;
    }

    /**
     * Маппинг TechniqueEntity → TechniqueDto
     */
    private TechniqueDto toTechniqueDto(TechniqueEntity entity) {
        if (entity == null) return null;
        TechniqueDto dto = new TechniqueDto();
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setOwner(entity.getOwner());
        dto.setDamage(entity.getDamage());
        return dto;
    }

    /**
     * Маппинг EconomicAssessmentEntity → EconomicAssessmentDto
     */
    private EconomicAssessmentDto toEconomicAssessmentDto(EconomicAssessmentEntity entity) {
        if (entity == null) return null;
        EconomicAssessmentDto dto = new EconomicAssessmentDto();
        dto.setTotalDamageCost(entity.getTotalDamageCost());
        dto.setInfrastructureDamage(entity.getInfrastructureDamage());
        dto.setCommercialDamage(entity.getCommercialDamage());
        dto.setTransportDamage(entity.getTransportDamage());
        dto.setRecoveryEstimateDays(entity.getRecoveryEstimateDays());
        dto.setInsuranceCovered(entity.getInsuranceCovered());
        return dto;
    }

    /**
     * Маппинг CivilianImpactEntity → CivilianImpactDto
     */
    private CivilianImpactDto toCivilianImpactDto(CivilianImpactEntity entity) {
        if (entity == null) return null;
        CivilianImpactDto dto = new CivilianImpactDto();
        dto.setEvacuated(entity.getEvacuated());
        dto.setInjured(entity.getInjured());
        dto.setMissing(entity.getMissing());
        dto.setPublicExposureRisk(entity.getPublicExposureRisk());
        return dto;
    }

    /**
     * Маппинг EnemyActivityEntity → EnemyActivityDto
     */
    private EnemyActivityDto toEnemyActivityDto(EnemyActivityEntity entity) {
        if (entity == null) return null;
        EnemyActivityDto dto = new EnemyActivityDto();
        dto.setBehaviorType(entity.getBehaviorType());
        dto.setTargetPriority(entity.getTargetPriority());
        dto.setAttackPatterns(entity.getAttackPatterns());
        dto.setMobility(entity.getMobility());
        dto.setEscalationRisk(entity.getEscalationRisk());
        dto.setCountermeasuresUsed(entity.getCountermeasuresUsed());
        return dto;
    }

    /**
     * Маппинг EnvironmentConditionsEntity → EnvironmentConditionsDto
     */
    private EnvironmentConditionsDto toEnvironmentConditionsDto(EnvironmentConditionsEntity entity) {
        if (entity == null) return null;
        EnvironmentConditionsDto dto = new EnvironmentConditionsDto();
        dto.setWeather(entity.getWeather());
        dto.setTimeOfDay(entity.getTimeOfDay());
        dto.setVisibility(entity.getVisibility());
        dto.setCursedEnergyDensity(entity.getCursedEnergyDensity());
        return dto;
    }

    /**
     * Маппинг OperationTimelineEntity → OperationTimelineDto
     */
    private OperationTimelineDto toTimelineDto(OperationTimelineEntity entity) {
        if (entity == null) return null;
        OperationTimelineDto dto = new OperationTimelineDto();
        dto.setTimestamp(entity.getTimestamp());
        dto.setType(entity.getType());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}