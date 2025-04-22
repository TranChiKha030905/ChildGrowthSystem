
package com.childgrowth.tracking.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String redirectToRoleDashboard(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String role = authentication.getAuthorities().iterator().next().getAuthority();
            return switch (role) {
                case "ROLE_ADMIN" -> "redirect:/admin/dashboard";
                case "ROLE_DOCTOR" -> "redirect:/doctor/dashboard";
                case "ROLE_MEMBER" -> "redirect:/member/dashboard";
                default -> "redirect:/";
            };
        }
        return "redirect:/login";
    }
}
