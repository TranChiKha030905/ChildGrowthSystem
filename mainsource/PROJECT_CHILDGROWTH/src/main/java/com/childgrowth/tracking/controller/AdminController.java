
package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.model.DoctorApproval;
import com.childgrowth.tracking.model.MembershipPlan;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.repository.MembershipPlanRepository;
import com.childgrowth.tracking.repository.UserRepository;
import com.childgrowth.tracking.service.DoctorApprovalService;
import com.childgrowth.tracking.service.DoctorService;
import com.childgrowth.tracking.service.UserService;
import com.childgrowth.tracking.service.impl.DoctorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DoctorApprovalService doctorApprovalService;
    private final UserService userService;
    private final DoctorService doctorService;
    private final UserRepository userRepository;

    private final MembershipPlanRepository membershipPlanRepository;

    @GetMapping("")
    public String index(){
        return "admin/index";
    }
    @GetMapping("/dashboard")
    public String dashboard() {
        return "/admin/dashboard";
    }

    @GetMapping("/doctors/pending")
    public String viewPendingDoctors(Model model) {
        model.addAttribute("pendingApprovals", doctorApprovalService.getPendingApprovals());
        return "/admin/pending-doctors";
    }

    @PostMapping("/doctors/approve/{id}")
    public String approveDoctor(@PathVariable Long id) {
        userService.approveDoctor(id);
        return "redirect:/admin/doctors/pending";
    }

    @PostMapping("/doctors/reject/{id}")
    public String rejectDoctor(@PathVariable Long id, @RequestParam String reason) {
        userService.rejectDoctor(id, reason);
        return "redirect:/admin/doctors/pending";
    }


    //---------------------DOCTOR----------------------------
    //tạo tài khoảng doctor
    @GetMapping("/doctor/add")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new User());  // truyền object vào model
        return "/admin/doctor/add-doctor";
    }

    @PostMapping("/doctor/add")
    public String addDoctor(@ModelAttribute("doctor") User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        // Kiểm tra lỗi validation từ annotation
        if (bindingResult.hasErrors()) {
            return "admin/doctor/add-doctor"; // Trả về form nếu có lỗi
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
            return "admin/doctor/add-doctor";
        }

        try {
            // Thiết lập enabled và lưu user
            user.setEnabled(true);
            user.setRole("DOCTOR");
            userService.saveDoctor(user);

            // Thêm thông báo thành công
            redirectAttributes.addFlashAttribute("message", "Thêm bác sĩ thành công!");
            return "redirect:/admin/doctor/manage";
        } catch (RuntimeException e) {
            // Xử lý các lỗi khác (nếu có)
            model.addAttribute("error", "Đã có lỗi xảy ra: " + e.getMessage());
            return "/admin/doctor/add-doctor";
        }
    }

    // Hiển thị danh sách bác sĩ
    @GetMapping("/doctor/manage")
    public String listDoctors(Model model) {
        model.addAttribute("doctors", userService.getAllDoctors()); // Lấy tất cả bác sĩ
        return "/admin/doctor/manage-doctors";

    }


    // Hiển thị form chỉnh sửa
    @GetMapping("/doctor/{id}/edit")
    public String showEditDoctorForm(@PathVariable Long id, Model model) {
        User doctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor", doctor);
        return "/admin/doctor/edit-doctor";
    }

    // Xử lý cập nhật
    @PostMapping("/doctor/{id}/edit")
    public String updateDoctor(@PathVariable Long id, @ModelAttribute("doctor") User updatedDoctor) {
        doctorService.updateDoctor(id, updatedDoctor);
        return "redirect:/admin/doctor/manage";
    }

    // Xử lý xoá bác sĩ
    @PostMapping("/doctor/{id}/delete")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/admin/doctor/manage";
    }

    //-----------------------member-----------------------
    //tạo tài khoảng member
    @GetMapping("/member/add")
    public String showAddMemberForm(Model model) {
        model.addAttribute("member", new User());  // truyền object vào model
        return "/admin/member/add-member";
    }

    @PostMapping("/member/add")
    public String addMember(@ModelAttribute("member") User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        // Kiểm tra lỗi validation từ annotation
        if (bindingResult.hasErrors()) {
            return "admin/member/add-member"; // Trả về form nếu có lỗi
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
            return "admin/member/add-member";
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
            redirectAttributes.addFlashAttribute("message", "Thêm thành viên thành công!");
            return "redirect:/admin/member/manage";
        } catch (RuntimeException e) {
            // Xử lý các lỗi khác (nếu có)
            model.addAttribute("error", "Đã có lỗi xảy ra: " + e.getMessage());
            return "/admin/member/add-member";
        }
    }


    // Hiển thị danh sách member
    @GetMapping("/member/manage")
    public String listMembers(Model model) {
        model.addAttribute("members", userService.getAllMembers()); // Lấy tất cả bác sĩ
        return "/admin/member/manage-members";

    }


    // Hiển thị form chỉnh sửa
    @GetMapping("/member/{id}/edit")
    public String showEditMemberForm(@PathVariable Long id, Model model) {
        User member = userService.getUserById(id);
        // Chỉ lấy id và name của các gói
        List<Map<String, Object>> plans = membershipPlanRepository.findAllPlansSimplified();
        model.addAttribute("member", member);
        model.addAttribute("plans", plans); // Truyền danh sách gói đơn giản
        return "/admin/member/edit-member";
    }

    // Xử lý cập nhật
    @PostMapping("/member/{id}/edit")
    public String updateMember(@PathVariable Long id, @ModelAttribute("member") User updatedMember, @RequestParam(required = false) Long membershipPlanId) {
        // Xử lý gói thành viên
        if (membershipPlanId != null) {
            MembershipPlan plan = new MembershipPlan();
            plan.setId(membershipPlanId); // Chỉ cần set ID là đủ
            updatedMember.setIdMembership(plan);
        } else {
            updatedMember.setIdMembership(null);
        }

        userService.updateUser(id, updatedMember);
        return "redirect:/admin/member/manage";
    }

    // Xử lý xoá bác sĩ
    @PostMapping("/member/{id}/delete")
    public String deleteMember(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/member/manage";
    }

    //------------------------------------------------------------------
    //xem profile của người đó (member,doctor)
    @GetMapping("/{username}")
    public String viewUser(@PathVariable String username, Model model) {
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "user/profile";
    }

}
