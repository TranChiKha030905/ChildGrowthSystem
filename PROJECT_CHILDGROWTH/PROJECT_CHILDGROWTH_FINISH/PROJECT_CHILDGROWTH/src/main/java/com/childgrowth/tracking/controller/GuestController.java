
package com.childgrowth.tracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
