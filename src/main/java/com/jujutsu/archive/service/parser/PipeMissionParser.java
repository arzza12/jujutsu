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


@Component("pipeParser")
public class PipeMissionParser implements MissionParser {

    @Override
    public MissionDto parse(InputStream inputStream) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        );

        MissionDto mission = new MissionDto();
        CurseDto curse = null;
        CivilianImpactDto civilianImpact = null;
        EnemyActivityDto enemyActivity = null;

        List<SorcererDto> sorcerers = new ArrayList<>();
        List<TechniqueDto> techniques = new ArrayList<>();
        List<OperationTimelineDto> timeline = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) continue;

            // Разбиваем строку по символу |
            String[] parts = line.split("\\|");
            if (parts.length == 0) continue;

            String recordType = parts[0].trim().toUpperCase();

            switch (recordType) {

                case "MISSION_CREATED" -> {
                    if (parts.length >= 2) mission.setMissionId(parts[1].trim());
                    if (parts.length >= 3) mission.setDate(LocalDate.parse(parts[2].trim()));
                    if (parts.length >= 4) mission.setLocation(parts[3].trim());
                }

                case "CURSE_DETECTED" -> {
                    curse = new CurseDto();
                    if (parts.length >= 2) curse.setName(parts[1].trim());
                    if (parts.length >= 3) curse.setThreatLevel(ThreatLevel.valueOf(parts[2].trim()));
                }
                case "SORCERER_ASSIGNED" -> {
                    SorcererDto s = new SorcererDto();
                    if (parts.length >= 2) s.setName(parts[1].trim());
                    if (parts.length >= 3) s.setRank(SorcererRank.valueOf(parts[2].trim()));
                    sorcerers.add(s);
                }

                case "TECHNIQUE_USED" -> {
                    TechniqueDto t = new TechniqueDto();
                    if (parts.length >= 2) t.setName(parts[1].trim());
                    if (parts.length >= 3) t.setType(TechniqueType.valueOf(parts[2].trim()));
                    if (parts.length >= 4) t.setOwner(parts[3].trim());
                    if (parts.length >= 5) t.setDamage(Double.parseDouble(parts[4].trim()));
                    techniques.add(t);
                }


                case "TIMELINE_EVENT" -> {
                    OperationTimelineDto e = new OperationTimelineDto();
                    if (parts.length >= 2) e.setTimestamp(LocalDateTime.parse(parts[1].trim()));
                    if (parts.length >= 3) e.setType(parts[2].trim());
                    if (parts.length >= 4) e.setDescription(parts[3].trim());
                    timeline.add(e);
                }

                case "ENEMY_ACTION" -> {
                    if (enemyActivity == null) enemyActivity = new EnemyActivityDto();
                    if (enemyActivity.getAttackPatterns() == null)
                        enemyActivity.setAttackPatterns(new ArrayList<>());
                    String actionType = parts.length >= 2 ? parts[1].trim() : "";
                    String actionDesc = parts.length >= 3 ? parts[2].trim() : "";
                    enemyActivity.getAttackPatterns().add(actionType + ": " + actionDesc);
                }

                case "CIVILIAN_IMPACT" -> {
                    civilianImpact = new CivilianImpactDto();
                    for (int i = 1; i < parts.length; i++) {
                        String[] kv = parts[i].trim().split("=");
                        if (kv.length == 2) {
                            switch (kv[0].trim()) {
                                case "evacuated" -> civilianImpact.setEvacuated(Integer.parseInt(kv[1].trim()));
                                case "injured"   -> civilianImpact.setInjured(Integer.parseInt(kv[1].trim()));
                                case "missing"   -> civilianImpact.setMissing(Integer.parseInt(kv[1].trim()));
                            }
                        }
                    }
                }

                case "MISSION_RESULT" -> {
                    if (parts.length >= 2) mission.setOutcome(Outcome.valueOf(parts[1].trim()));
                    if (parts.length >= 3) {
                        String[] kv = parts[2].trim().split("=");
                        if (kv.length == 2 && "damageCost".equals(kv[0].trim()))
                            mission.setDamageCost(Double.parseDouble(kv[1].trim()));
                    }
                }

                default -> {}
            }
        }

        mission.setCurse(curse);
        mission.setCivilianImpact(civilianImpact);
        mission.setEnemyActivity(enemyActivity);

        if (!sorcerers.isEmpty())  mission.setSorcerers(sorcerers);
        if (!techniques.isEmpty()) mission.setTechniques(techniques);
        if (!timeline.isEmpty())   mission.setOperationTimeline(timeline);

        return mission;
    }
}