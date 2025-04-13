
package com.childgrowth.tracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MembershipController {
    @GetMapping("/memberships")
    public String showMemberships() {
        return "memberships"; // trang hiển thị các gói thành viên
    }
}
