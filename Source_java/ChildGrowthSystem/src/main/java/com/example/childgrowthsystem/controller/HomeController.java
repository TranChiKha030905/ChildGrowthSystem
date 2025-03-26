package com.example.childgrowthsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping("/feedback")
    public String feedback() {
        return "feedback";
    }

}
