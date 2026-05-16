package com.jujutsu.archive.controller;

import com.jujutsu.archive.model.dto.MissionDto;
import com.jujutsu.archive.service.MissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }


    @GetMapping("/")
    public String index(Model model) {
        List<MissionDto> missions = missionService.getAllMissions();
        model.addAttribute("missions", missions);
        model.addAttribute("totalCount", missions.size());

        long successCount = missions.stream()
                .filter(m -> m.getOutcome() != null &&
                        m.getOutcome().name().contains("SUCCESS"))
                .count();
        long failureCount = missions.stream()
                .filter(m -> m.getOutcome() != null &&
                        m.getOutcome().name().equals("FAILURE"))
                .count();

        model.addAttribute("successCount", successCount);
        model.addAttribute("failureCount", failureCount);

        return "index";
    }

    @GetMapping("/upload")
    public String uploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("format") String format,
                             RedirectAttributes redirectAttributes) {
        try {
            MissionDto saved = missionService.saveMission(file, format);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Миссия [" + saved.getMissionId() + "] успешно добавлена в архив");
            return "redirect:/report/" + saved.getMissionId();

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/upload";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Ошибка обработки файла: " + e.getMessage());
            return "redirect:/upload";
        }
    }


    @GetMapping("/missions/{missionId}")
    @ResponseBody
    public MissionDto getMission(@PathVariable String missionId) {
        return missionService.getMissionById(missionId);
    }
}