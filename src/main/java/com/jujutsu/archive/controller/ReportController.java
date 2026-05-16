package com.jujutsu.archive.controller;

import com.jujutsu.archive.model.dto.MissionDto;
import com.jujutsu.archive.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @GetMapping("/report/{missionId}")
    public String report(@PathVariable String missionId,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        try {
            MissionDto report = reportService.buildReport(missionId);
            model.addAttribute("mission", report);

            model.addAttribute("hasCivilianImpact",   report.getCivilianImpact() != null);
            model.addAttribute("hasEconomicAssessment", report.getEconomicAssessment() != null);
            model.addAttribute("hasEnemyActivity",    report.getEnemyActivity() != null);
            model.addAttribute("hasEnvironment",      report.getEnvironmentConditions() != null);
            model.addAttribute("hasTimeline",
                    report.getOperationTimeline() != null && !report.getOperationTimeline().isEmpty());
            model.addAttribute("hasSorcerers",
                    report.getSorcerers() != null && !report.getSorcerers().isEmpty());
            model.addAttribute("hasTechniques",
                    report.getTechniques() != null && !report.getTechniques().isEmpty());

            return "report";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/";
        }
    }
}