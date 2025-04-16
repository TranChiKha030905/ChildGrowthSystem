
package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.model.MembershipPlan;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.repository.UserRepository;
import com.childgrowth.tracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // hiển thị form đăng nhập
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        User user= new User();
        model.addAttribute("user", user);
        return "register";
    }

//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute("user") User user, Model model) {
//        if (user.getRole() == null || user.getRole().isEmpty()) {
//            user.setEnabled(true);
//            user.setRole("MEMBER"); // set mặc định nếu không được chọn trong form
//        }
//        try {
//            userService.registerUser(user);
//            return "redirect:/login?success";
//        } catch (RuntimeException e) {
//            model.addAttribute("error", e.getMessage());
//            return "register";
//        }
//    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        // Kiểm tra lỗi validation từ annotation
        if (bindingResult.hasErrors()) {
            return "register"; // Trả về form nếu có lỗi
        }

        // Kiểm tra trùng username
        if (userRepository.existsByUsername(user.getUsername())) {
            bindingResult.rejectValue("username", "error.member", "Username đã tồn tại");
        }

        // Kiểm tra trùng email
        if (userRepository.existsByEmail(user.getEmail())) {
            bindingResult.rejectValue("email", "error.member", "Email đã tồn tại");
        }

        // Nếu có lỗi (trùng username hoặc email), trả về form
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            // Thiết lập enabled và lưu user
            user.setEnabled(true);
            user.setRole("MEMBER");
            user.setMoney(0L);
            user.setIdMembership(new MembershipPlan(1L));
            userService.saveMember(user);

            // Thêm thông báo thành công
            redirectAttributes.addFlashAttribute("message", "Thêm tài khoảng thành công!");
            return "redirect:/login?success";
        } catch (RuntimeException e) {
            // Xử lý các lỗi khác (nếu có)
            model.addAttribute("error", "Đã có lỗi xảy ra: " + e.getMessage());
            return "register";
        }
    }


}
