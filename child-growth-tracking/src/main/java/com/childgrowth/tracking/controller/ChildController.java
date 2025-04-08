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

    @GetMapping
    public String viewChildren(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        if (currentUser == null) {
            return "redirect:/login";
        }
        User user = userService.findByUsername(currentUser.getUsername());
        List<ChildProfile> children = childService.getChildrenByUser(user);
        model.addAttribute("children", children);
        return "child/list";
    }

    @GetMapping("/add")
    public String addChildForm(Model model) {
        model.addAttribute("child", new ChildProfile());
        return "child/add";
    }

    @PostMapping("/add")
    public String saveChild(@ModelAttribute("child") ChildProfile child,
                            @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUsername(currentUser.getUsername());
        child.setParent(user); // Gán cha/mẹ cho trẻ
        childService.saveChild(child);
        return "redirect:/children";
    }
}
