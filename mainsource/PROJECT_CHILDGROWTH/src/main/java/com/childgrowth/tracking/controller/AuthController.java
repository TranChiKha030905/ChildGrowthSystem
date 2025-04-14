
package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // hiển thị form đăng nhập
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        User user= new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("MEMBER"); // set mặc định nếu không được chọn trong form
        }
        try {
            userService.registerUser(user);
            return "redirect:/login?success";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

}
