
package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public String viewUser(@PathVariable String username, Model model) {
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "user/profile"; // cần tạo user/profile.html nếu muốn dùng
    }
}
