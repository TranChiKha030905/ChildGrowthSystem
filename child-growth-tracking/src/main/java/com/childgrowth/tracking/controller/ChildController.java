package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.model.ChildProfile;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.service.ChildProfileService;
import com.childgrowth.tracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/children")
public class ChildController {

    @Autowired
    private ChildProfileService childService;

    @Autowired
    private UserService userService;

    // Hiển thị danh sách trẻ
    @GetMapping
    public String viewChildren(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUsername(currentUser.getUsername());
        List<ChildProfile> children = childService.getChildrenByUser(user);
        model.addAttribute("children", children);
        return "list"; //
    }

    // Trang thêm mới trẻ
    @GetMapping("/add")
    public String addChildForm(Model model) {
        model.addAttribute("child", new ChildProfile());
        return "add-child"; // ⚠️
    }

    // Lưu trẻ mới
    @PostMapping("/add")
    public String saveChild(@ModelAttribute("child") ChildProfile child,
                            @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUsername(currentUser.getUsername());
        child.setParent(user);
        childService.saveChild(child);
        return "redirect:/children";
    }
}
