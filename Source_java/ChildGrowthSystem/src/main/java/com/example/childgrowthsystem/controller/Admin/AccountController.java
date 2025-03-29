package com.example.childgrowthsystem.controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AccountController {
    @RequestMapping("/admin/Account")
    public String account() {
        return "admin/Account/index";
    }
    @RequestMapping("/admin/add-Account")
    public String accountAdd() {
        return "admin/Account/add";
    }
}
