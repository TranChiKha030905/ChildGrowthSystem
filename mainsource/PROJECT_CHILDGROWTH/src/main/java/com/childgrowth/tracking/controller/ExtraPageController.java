
package com.childgrowth.tracking.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import com.childgrowth.tracking.model.Feedback;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.service.FeedbackService;
import com.childgrowth.tracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;


@Controller
@RequiredArgsConstructor
public class ExtraPageController {

    private final FeedbackService feedbackService;
    private final UserService userService;

    @GetMapping("/service")
    public String service() {
        return "service";
    }

    @GetMapping("/feedback")
    public String feedback() {
        return "feedback";
    }
    @GetMapping("/member/feedback")
    public String showFeedbackPage(Authentication authentication, Model model) {
        User currentUser = userService.getUserByUsername(authentication.getName());

        model.addAttribute("feedback", new Feedback()); // Sử dụng Entity trực tiếp
        model.addAttribute("feedbacks", feedbackService.getFeedbackByUser(currentUser));
        return "member-feedback";
    }

    // Xử lý gửi feedback
    @PostMapping("/feedback")
    @PreAuthorize("isAuthenticated()")
    public String submitFeedback(@ModelAttribute("feedback") Feedback feedback,
                                 BindingResult result,
                                 Authentication authentication,
                                 RedirectAttributes redirectAttributes) {

        User currentUser = userService.getUserByUsername(authentication.getName());
        feedback.setUser(currentUser);
        feedback.setCreatedAt(LocalDateTime.now());
        feedback.setType("USER_FEEDBACK");
        feedback.setStatus("PENDING");

        feedbackService.saveFeedback(feedback);

        redirectAttributes.addFlashAttribute("success", "Cảm ơn bạn đã gửi phản hồi!");
        return "redirect:/member/feedback";
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
