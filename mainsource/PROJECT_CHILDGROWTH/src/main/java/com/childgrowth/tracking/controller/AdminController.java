
package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.model.DoctorApproval;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.service.DoctorApprovalService;
import com.childgrowth.tracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DoctorApprovalService doctorApprovalService;
    private final UserService userService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/members")
    public String viewMembers(Model model) {
        model.addAttribute("members", userService.getAllUsers());
        return "admin/members";
    }

    @GetMapping("/doctors/pending")
    public String viewPendingDoctors(Model model) {
        model.addAttribute("pendingApprovals", doctorApprovalService.getPendingApprovals());
        return "admin/pending-doctors";
    }

    @PostMapping("/doctors/approve/{id}")
    public String approveDoctor(@PathVariable Long id) {
        userService.approveDoctor(id);
        return "redirect:/admin/doctors/pending";
    }

    @PostMapping("/doctors/reject/{id}")
    public String rejectDoctor(@PathVariable Long id, @RequestParam String reason) {
        userService.rejectDoctor(id, reason);
        return "redirect:/admin/doctors/pending";
    }
}
