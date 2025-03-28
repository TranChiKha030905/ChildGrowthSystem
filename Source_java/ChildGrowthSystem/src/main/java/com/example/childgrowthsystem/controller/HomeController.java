package com.example.childgrowthsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }


    @GetMapping("/feedback")
    public String feedback() {
        return "feedback";
    }

    @GetMapping("/pricing")
    public String pricing() {
        return "pricing";
    }

    @GetMapping("/service")
    public String service() {
        return "service";
    }
}
