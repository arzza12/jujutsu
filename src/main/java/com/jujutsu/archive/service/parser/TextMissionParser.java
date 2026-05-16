package com.jujutsu.archive.service.parser;

import com.jujutsu.archive.enums.*;
import com.jujutsu.archive.model.dto.*;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component("textParser")
public class TextMissionParser implements MissionParser {

    @Override
    public MissionDto parse(InputStream inputStream) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        );

        MissionDto mission = new MissionDto();
        CurseDto curse = null;
        EnvironmentConditionsDto environment = null;
        CivilianImpactDto civilianImpact = null;
        EconomicAssessmentDto economicAssessment = null;
        EnemyActivityDto enemyActivity = null;

        List<SorcererDto> sorcerers = new ArrayList<>();
        List<TechniqueDto> techniques = new ArrayList<>();
        List<OperationTimelineDto> timeline = new ArrayList<>();
        List<String> operationTags = new ArrayList<>();
        List<String> supportUnits = new ArrayList<>();
        List<String> recommendations = new ArrayList<>();
        List<String> artifactsRecovered = new ArrayList<>();
        List<String> evacuationZones = new ArrayList<>();
        List<String> statusEffects = new ArrayList<>();

        SorcererDto currentSorcerer = null;
        TechniqueDto currentTechnique = null;
        OperationTimelineDto currentTimeline = null;
        String currentSection = null;

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;

            if (line.startsWith("[") && line.endsWith("]")) {
                if (currentSorcerer != null)  { sorcerers.add(currentSorcerer);  currentSorcerer = null; }
                if (currentTechnique != null) { techniques.add(currentTechnique); currentTechnique = null; }
                if (currentTimeline != null)  { timeline.add(currentTimeline);   currentTimeline = null; }

                currentSection = line.substring(1, line.length() - 1).toUpperCase();

                switch (currentSection) {
                    case "SORCERER"     -> currentSorcerer = new SorcererDto();
                    case "TECHNIQUE"    -> currentTechnique = new TechniqueDto();
                    case "TIMELINE"     -> currentTimeline = new OperationTimelineDto();
                    case "CURSE"        -> curse = new CurseDto();
                    case "ENVIRONMENT"  -> environment = new EnvironmentConditionsDto();
                    case "CIVILIAN"     -> civilianImpact = new CivilianImpactDto();
                    case "ECONOMIC"     -> economicAssessment = new EconomicAssessmentDto();
                    case "ENEMY"        -> enemyActivity = new EnemyActivityDto();
                }
                continue;
            }

            int sep = line.indexOf('=');
            if (sep <= 0 || currentSection == null) continue;

            String key   = line.substring(0, sep).trim();
            String value = line.substring(sep + 1).trim();

            switch (currentSection) {
                case "MISSION"     -> parseMissionField(mission, key, value);
                case "CURSE"       -> { if (curse != null) parseCurseField(curse, key, value); }
                case "SORCERER"    -> { if (currentSorcerer != null) parseSorcererField(currentSorcerer, key, value); }
                case "TECHNIQUE"   -> { if (currentTechnique != null) parseTechniqueField(currentTechnique, key, value); }
                case "ENVIRONMENT" -> { if (environment != null) parseEnvironmentField(environment, key, value); }
                case "CIVILIAN"    -> { if (civilianImpact != null) parseCivilianField(civilianImpact, key, value); }
                case "ECONOMIC"    -> { if (economicAssessment != null) parseEconomicField(economicAssessment, key, value); }
                case "TIMELINE"    -> { if (currentTimeline != null) parseTimelineField(currentTimeline, key, value); }
                case "NOTES"       -> { if ("text".equals(key)) mission.setNotes(value); }
                case "TAGS"        -> { if ("tag".equals(key)) operationTags.add(value); }
                case "UNITS"       -> { if ("unit".equals(key)) supportUnits.add(value); }
                case "RECOMMEND"   -> { if ("recommendation".equals(key)) recommendations.add(value); }
                case "ARTIFACTS"   -> { if ("artifact".equals(key)) artifactsRecovered.add(value); }
                case "ZONES"       -> { if ("zone".equals(key)) evacuationZones.add(value); }
                case "EFFECTS"     -> { if ("effect".equals(key)) statusEffects.add(value); }
                case "ENEMY"       -> {
                    if (enemyActivity == null) break;
                    switch (key) {
                        case "behaviorType"   -> enemyActivity.setBehaviorType(BehaviorType.valueOf(value));
                        case "mobility"       -> enemyActivity.setMobility(Mobility.valueOf(value));
                        case "escalationRisk" -> enemyActivity.setEscalationRisk(EscalationRisk.valueOf(value));
                        case "targetPriority" -> {
                            if (enemyActivity.getTargetPriority() == null)
                                enemyActivity.setTargetPriority(new ArrayList<>());
                            enemyActivity.getTargetPriority().add(value);
                        }
                        case "attackPatterns" -> {
                            if (enemyActivity.getAttackPatterns() == null)
                                enemyActivity.setAttackPatterns(new ArrayList<>());
                            enemyActivity.getAttackPatterns().add(value);
                        }
                        case "countermeasures" -> {
                            if (enemyActivity.getCountermeasuresUsed() == null)
                                enemyActivity.setCountermeasuresUsed(new ArrayList<>());
                            enemyActivity.getCountermeasuresUsed().add(value);
                        }
                    }
                }
            }
        }

        if (currentSorcerer != null)  sorcerers.add(currentSorcerer);
        if (currentTechnique != null) techniques.add(currentTechnique);
        if (currentTimeline != null)  timeline.add(currentTimeline);

        mission.setCurse(curse);
        mission.setEnvironmentConditions(environment);
        mission.setCivilianImpact(civilianImpact);
        mission.setEconomicAssessment(economicAssessment);
        mission.setEnemyActivity(enemyActivity);

        if (!sorcerers.isEmpty())          mission.setSorcerers(sorcerers);
        if (!techniques.isEmpty())         mission.setTechniques(techniques);
        if (!timeline.isEmpty())           mission.setOperationTimeline(timeline);
        if (!operationTags.isEmpty())      mission.setOperationTags(operationTags);
        if (!supportUnits.isEmpty())       mission.setSupportUnits(supportUnits);
        if (!recommendations.isEmpty())    mission.setRecommendations(recommendations);
        if (!artifactsRecovered.isEmpty()) mission.setArtifactsRecovered(artifactsRecovered);
        if (!evacuationZones.isEmpty())    mission.setEvacuationZones(evacuationZones);
        if (!statusEffects.isEmpty())      mission.setStatusEffects(statusEffects);

        return mission;
    }

    private void parseMissionField(MissionDto m, String key, String value) {
        switch (key) {
            case "missionId"  -> m.setMissionId(value);
            case "date"       -> m.setDate(LocalDate.parse(value));
            case "location"   -> m.setLocation(value);
            case "outcome"    -> m.setOutcome(Outcome.valueOf(value));
            case "damageCost" -> m.setDamageCost(Double.parseDouble(value));
            case "notes"      -> m.setNotes(value);
        }
    }

    private void parseCurseField(CurseDto c, String key, String value) {
        switch (key) {
            case "name"        -> c.setName(value);
            case "threatLevel" -> c.setThreatLevel(ThreatLevel.valueOf(value));
        }
    }

    private void parseSorcererField(SorcererDto s, String key, String value) {
        switch (key) {
            case "name" -> s.setName(value);
            case "rank" -> s.setRank(SorcererRank.valueOf(value));
        }
    }

    private void parseTechniqueField(TechniqueDto t, String key, String value) {
        switch (key) {
            case "name"   -> t.setName(value);
            case "type"   -> t.setType(TechniqueType.valueOf(value));
            case "owner"  -> t.setOwner(value);
            case "damage" -> t.setDamage(Double.parseDouble(value));
        }
    }

    private void parseEnvironmentField(EnvironmentConditionsDto e, String key, String value) {
        switch (key) {
            case "weather"             -> e.setWeather(Weather.valueOf(value));
            case "timeOfDay"           -> e.setTimeOfDay(TimeOfDay.valueOf(value));
            case "visibility"          -> e.setVisibility(Visibility.valueOf(value));
            case "cursedEnergyDensity" -> e.setCursedEnergyDensity(Double.parseDouble(value));
        }
    }

    private void parseCivilianField(CivilianImpactDto c, String key, String value) {
        switch (key) {
            case "evacuated"          -> c.setEvacuated(Integer.parseInt(value));
            case "injured"            -> c.setInjured(Integer.parseInt(value));
            case "missing"            -> c.setMissing(Integer.parseInt(value));
            case "publicExposureRisk" -> c.setPublicExposureRisk(PublicExposureRisk.valueOf(value));
        }
    }

    private void parseEconomicField(EconomicAssessmentDto e, String key, String value) {
        switch (key) {
            case "totalDamageCost"      -> e.setTotalDamageCost(Double.parseDouble(value));
            case "infrastructureDamage" -> e.setInfrastructureDamage(Double.parseDouble(value));
            case "commercialDamage"     -> e.setCommercialDamage(Double.parseDouble(value));
            case "transportDamage"      -> e.setTransportDamage(Double.parseDouble(value));
            case "recoveryEstimateDays" -> e.setRecoveryEstimateDays(Double.parseDouble(value));
            case "insuranceCovered"     -> e.setInsuranceCovered(Boolean.parseBoolean(value));
        }
    }

    private void parseTimelineField(OperationTimelineDto t, String key, String value) {
        switch (key) {
            case "timestamp"   -> t.setTimestamp(LocalDateTime.parse(value));
            case "type"        -> t.setType(value);
            case "description" -> t.setDescription(value);
        }
    }
}