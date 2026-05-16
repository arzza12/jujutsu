package com.jujutsu.archive.service;

import com.jujutsu.archive.model.dto.MissionDto;
import org.springframework.stereotype.Service;


@Service
public class ReportService {

    private final MissionService missionService;

    public ReportService(MissionService missionService) {
        this.missionService = missionService;
    }

    public MissionDto buildReport(String missionId) {
        MissionDto dto = missionService.getMissionById(missionId);

        return dto;
    }

    public java.util.List<MissionDto> getAllMissionsSummary() {
        return missionService.getAllMissions();
    }
}