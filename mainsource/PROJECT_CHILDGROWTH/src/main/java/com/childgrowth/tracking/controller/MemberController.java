
package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.model.Child;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.service.ChildService;
import com.childgrowth.tracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final UserService userService;
    private final ChildService childService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        User parent = userService.getUserByUsername(authentication.getName());
        model.addAttribute("children", childService.getChildrenByParent(parent));
        return "member/dashboard";
    }

    @GetMapping("/children")
    public String viewChildren(Model model, Authentication authentication) {
        User parent = userService.getUserByUsername(authentication.getName());
        model.addAttribute("children", childService.getChildrenByParent(parent));
        return "member/children";
    }

    @GetMapping("/add-child")
    public String showAddChildForm(Model model) {
        model.addAttribute("child", new Child());
        return "member/add-child";
    }

    @PostMapping("/add-child")
    public String addChild(@ModelAttribute("child") Child child, Authentication authentication) {
        User parent = userService.getUserByUsername(authentication.getName());
        child.setParent(parent);
        childService.saveChild(child);
        return "redirect:/member/children";
    }
}
