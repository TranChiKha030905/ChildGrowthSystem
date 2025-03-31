package com.example.childgrowth.controller;

import com.example.childgrowth.model.Child;
import com.example.childgrowth.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChildController {

    @Autowired
    private ChildService childService;

    // Trang chính (Dashboard)
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("children", childService.getAllChildren());
        return "index";
    }

    // Form thêm thông tin trẻ
    @GetMapping("/add")
    public String addChildForm(Model model) {
        model.addAttribute("child", new Child());
        return "add-child";
    }

    // Xử lý thêm thông tin trẻ
    @PostMapping("/add")
    public String addChild(@ModelAttribute Child child) {
        childService.saveChild(child);
        return "redirect:/";
    }
}