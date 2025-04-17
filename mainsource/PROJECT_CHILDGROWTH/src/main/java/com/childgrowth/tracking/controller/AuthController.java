
package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.model.MembershipPlan;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.repository.MembershipPlanRepository;
import com.childgrowth.tracking.repository.UserRepository;
import com.childgrowth.tracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final MembershipPlanRepository membershipPlanRepository;

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

            MembershipPlan defaultPlan = membershipPlanRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Gói mặc định không tồn tại"));
            user.setIdMembership(defaultPlan);

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

//    ----------------------------------quên mật khẩu------------
private final PasswordEncoder passwordEncoder; // inject qua constructor

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String handleForgotPassword(@RequestParam("email") String email, Model model) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            model.addAttribute("message", "Không tìm thấy người dùng với email này.");
        } else {
            // Tạo mật khẩu tạm thời
            String tempPassword = generateTempPassword();

            // Mã hóa mật khẩu
            String encodedPassword = passwordEncoder.encode(tempPassword);
            user.setPassword(encodedPassword);
            userRepository.save(user);

            model.addAttribute("message", "Mật khẩu đã được đặt lại thành công.");
            model.addAttribute("newPassword", tempPassword);
        }

        return "forgot-password";
    }

    // Hàm tạo mật khẩu tạm thời
    private String generateTempPassword() {
        return UUID.randomUUID().toString().substring(0, 8); // VD: "3fj29dka"
    }


    //---------------------------------Đặt lại mật khẩu---------------------------
    @GetMapping("/reset-password")
    public String showResetPasswordForm() {
        return "reset-password";
    }
    @PostMapping("/reset-password")
    public String handleResetPassword(@RequestParam("email") String email,
                                      @RequestParam("currentPassword") String currentPassword,
                                      @RequestParam("newPassword") String newPassword,
                                      RedirectAttributes redirectAttributes) {

        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Email không tồn tại.");
            return "redirect:/reset-password";
        }

        // So sánh mật khẩu hiện tại với mật khẩu đã mã hóa
        if (!userService.matchesPassword(currentPassword, user.getPassword())) {
            redirectAttributes.addFlashAttribute("message", "Mật khẩu hiện tại không đúng.");
            return "redirect:/reset-password";
        }

        // Cập nhật mật khẩu mới
        user.setPassword(userService.encodePassword(newPassword));
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("message", "Cập nhật mật khẩu thành công.");
        return "redirect:/login";
    }


}
