
package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final UserService userService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "doctor/dashboard";
    }

    @GetMapping("/requests")
    public String viewDoctorRequests(Model model) {
        model.addAttribute("doctors", userService.getAllUsers()); // Có thể thay bằng filter riêng
        return "doctor/requests";
    }
}
