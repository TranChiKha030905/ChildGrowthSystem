
package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ExtraPageController {


    @GetMapping("/service")
    public String service() {
        return "service";
    }

    @GetMapping("/feedback")
    public String feedback() {
        return "feedback";
    }

    @GetMapping("/growth-records")
    public String growthRecords() {
        return "growth-records";
    }

    @GetMapping("/admin/memberships")
    public String adminMemberships() {
        return "admin-memberships";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/doctor/patient-details")
    public String doctorPatientDetails() {
        return "doctor/patient-details";
    }

    @GetMapping("/doctor/reports")
    public String doctorReports() {
        return "doctor/reports";
    }

    @GetMapping("/member/advice-requests")
    public String memberAdviceRequests() {
        return "member/advice-requests";
    }

    @GetMapping("/member/child-details")
    public String memberChildDetails() {
        return "member/child-details";
    }
}
