package com.example.childgrowth.controller;

import com.example.childgrowth.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    @Autowired
    private ChildService childService;

    @GetMapping("/report")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public String showReport(Model model) {
        double averageBmi = childService.getAverageBmi();
        model.addAttribute("averageBmi", averageBmi);
        model.addAttribute("children", childService.getAllChildren());
        return "report";
    }
}