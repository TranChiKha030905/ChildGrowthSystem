package com.example.childgrowth.controller;

import com.example.childgrowth.model.*;
import com.example.childgrowth.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChildController {

    @Autowired
    private ChildService childService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('GUEST', 'MEMBER', 'DOCTOR', 'ADMIN')")
    public String home(Model model) {
        model.addAttribute("children", childService.getAllChildren());
        return "index";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyRole('MEMBER', 'DOCTOR', 'ADMIN')")
    public String addChildForm(Model model) {
        model.addAttribute("child", new ChildProfile());
        return "add-child";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('MEMBER', 'DOCTOR', 'ADMIN')")
    public String addChild(@ModelAttribute ChildProfile child) {
        childService.saveChild(child);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public String editChildForm(@PathVariable Long id, Model model) {
        ChildProfile child = childService.getAllChildren().stream()
                .filter(c -> c.getChildId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("child", child);
        return "edit-child";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public String editChild(@PathVariable Long id, @ModelAttribute ChildProfile child) {
        child.setChildId(id);
        childService.saveChild(child);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteChild(@PathVariable Long id) {
        childService.deleteChild(id);
        return "redirect:/";
    }

    // Thêm mốc phát triển
    @GetMapping("/milestone/add/{childId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'DOCTOR', 'ADMIN')")
    public String addMilestoneForm(@PathVariable Long childId, Model model) {
        model.addAttribute("milestone", new GrowthMilestone());
        model.addAttribute("childId", childId);
        return "add-milestone";
    }

    @PostMapping("/milestone/add")
    @PreAuthorize("hasAnyRole('MEMBER', 'DOCTOR', 'ADMIN')")
    public String addMilestone(@ModelAttribute GrowthMilestone milestone) {
        childService.addGrowthMilestone(milestone);
        return "redirect:/";
    }

    // Thêm lịch tiêm chủng
    @GetMapping("/vaccination/add/{childId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'DOCTOR', 'ADMIN')")
    public String addVaccinationForm(@PathVariable Long childId, Model model) {
        model.addAttribute("vaccination", new Vaccination());
        model.addAttribute("childId", childId);
        return "add-vaccination";
    }

    @PostMapping("/vaccination/add")
    @PreAuthorize("hasAnyRole('MEMBER', 'DOCTOR', 'ADMIN')")
    public String addVaccination(@ModelAttribute Vaccination vaccination) {
        childService.addVaccination(vaccination);
        return "redirect:/";
    }

    // Xem thông báo
    @GetMapping("/notifications/{userId}")
    @PreAuthorize("hasAnyRole('MEMBER', 'DOCTOR', 'ADMIN')")
    public String viewNotifications(@PathVariable Long userId, Model model) {
        List<Notification> notifications = childService.getUnreadNotifications(userId);
        model.addAttribute("notifications", notifications);
        return "notifications";
    }
}