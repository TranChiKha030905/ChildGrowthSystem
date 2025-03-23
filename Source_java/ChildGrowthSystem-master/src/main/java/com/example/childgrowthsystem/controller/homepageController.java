package com.example.childgrowthsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class homepageController {
    public homepageController() {}

    @GetMapping("/")
    public ModelAndView homepage() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
}
