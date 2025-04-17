
package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.model.Child;
import com.childgrowth.tracking.model.MembershipPlan;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.repository.ChildRepository;
import com.childgrowth.tracking.repository.MembershipPlanRepository;
import com.childgrowth.tracking.service.ChildService;
import com.childgrowth.tracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final UserService userService;
    private final ChildService childService;
    private final ChildRepository childRepository;

    private final MembershipPlanRepository membershipPlanRepository;

    // Trang hiển thị danh sách gói thành viên
    @GetMapping("/upgrade")
    public String showUpgradePage(Model model, Authentication authentication) {

        User user = userService.getUserByUsername(authentication.getName());
        List<MembershipPlan> plans = membershipPlanRepository.findAll();

        model.addAttribute("user", user);
        model.addAttribute("plans", plans);
        return "member/upgrade";
    }

    // Xử lý nâng cấp
    @PostMapping("/upgrade")
    public String upgradeMembership(@RequestParam Long planId,
                                    Authentication authentication,
                                    RedirectAttributes redirectAttributes) {
        User user = userService.getUserByUsername(authentication.getName());

        MembershipPlan selectedPlan = membershipPlanRepository.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("Gói không tồn tại."));

        Long userBalance = user.getMoney();
        int planPrice = selectedPlan.getPrice();

        if (userBalance < planPrice) {
            redirectAttributes.addFlashAttribute("error", "Bạn không đủ số dư để nâng cấp gói này. Số dư hiện tại: " + userBalance + " $.");
            redirectAttributes.addFlashAttribute("money", userBalance);
            return "redirect:/member/upgrade";
        }


        // Trừ tiền và cập nhật gói
        user.setMoney(userBalance - planPrice);
        user.setIdMembership(selectedPlan);
        userService.saveMember(user);

        redirectAttributes.addFlashAttribute("message", "Nâng cấp gói thành công! Bạn đã chuyển sang gói: " + selectedPlan.getName());
        return "redirect:/member/dashboard";
    }
    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        User parent = userService.getUserByUsername(authentication.getName());
        List<Child> children = childService.getChildrenByParent(parent);

        // Gợi ý thông báo nếu đã lâu chưa khám
        List<Child> needCheckup = children.stream()
                .filter(c -> c.getLastCheckup() == null ||
                        ChronoUnit.MONTHS.between(c.getLastCheckup(), LocalDate.now()) >= 6)
                .collect(Collectors.toList());

        // Gửi danh sách cần khám để hiển thị
        model.addAttribute("children", needCheckup);

        return "member/dashboard";
    }




//-------------------------------------------------------------
    //tạo tài khoảng của trẻ
    @GetMapping("/add-child")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("children", new Child());  // truyền object vào model
        return "/member/children/add-child";
    }

    @PostMapping("/add-child")
    public String addDoctor(@ModelAttribute("children") Child child,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes,
                            Authentication authentication) {
        User parent = userService.getUserByUsername(authentication.getName());

        // 🔒 Kiểm tra số lượng trẻ theo gói
        List<Child> existingChildren = childService.getChildrenByParent(parent);
        int currentCount = existingChildren.size();
        //số lượng trẻ tối đa của gói đã mua
        int maxAllowed = parent.getIdMembership().getMaxChildren();

        if (currentCount >= maxAllowed) {
            model.addAttribute("error", "Bạn đã đạt giới hạn số trẻ theo gói hiện tại. Hãy nâng cấp gói để thêm nhiều trẻ hơn.");
            return "member/children/add-child";
        }

        // Kiểm tra lỗi validation
        if (bindingResult.hasErrors()) {
            return "member/children/add-child";
        }

        // Kiểm tra trùng tên
        if (childRepository.existsByNameAndParent(child.getName(), parent)) {
            bindingResult.rejectValue("name", "error.child", "Tên đã tồn tại trong danh sách của bạn");
            return "member/children/add-child";
        }

        try {
            child.setParent(parent);
            childService.saveChild(child);
            redirectAttributes.addFlashAttribute("message", "Thêm trẻ thành công!");
            return "redirect:/member/manage";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Đã có lỗi xảy ra: " + e.getMessage());
            return "member/children/add-child";
        }
    }

    // Hiển thị danh sách trẻ của user
    @GetMapping("/manage")
    public String viewChildren(Model model, Authentication authentication) {
        User parent = userService.getUserByUsername(authentication.getName());
        List<Child> children = childService.getChildrenByParent(parent);
        // Gợi ý thông báo nếu đã lâu chưa khám
        List<Child> needCheckup = children.stream()
                .filter(c -> c.getLastCheckup() == null || ChronoUnit.MONTHS.between(c.getLastCheckup(), LocalDate.now()) >= 6)
                .collect(Collectors.toList());

        model.addAttribute("children", children);
        model.addAttribute("needCheckup", needCheckup);
        return "member/children/manage-child";
    }


    // Xem chi tiết trẻ
    @GetMapping("/child/{id}")
    public String viewChildDetail(@PathVariable Long id, Model model, Authentication authentication) {
        Optional<Child> child = childRepository.findById(id);
        if (child.isPresent()) {
            model.addAttribute("child", child.get());
            return "member/children/view-child";
        } else {
            return "redirect:/member/manage";
        }
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/child/{id}/edit")
    public String showEditChildForm(@PathVariable Long id, Model model, Authentication authentication) {
        Optional<Child> child = childRepository.findById(id);
        if (child.isPresent()) {
            model.addAttribute("children", child.get());
            return "member/children/edit-child";
        } else {
            return "redirect:/member/manage";
        }
    }

    // Xử lý cập nhật thông tin trẻ (bao gồm lastCheckup)
    @PostMapping("/child/{id}/edit")
    public String updateChild(@PathVariable Long id,
                              @ModelAttribute("children") Child updatedChild,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Authentication authentication) {
        Optional<Child> optionalChild = childRepository.findById(id);
        if (optionalChild.isPresent()) {
            Child existingChild = optionalChild.get();

            // Gán lại các trường cần cập nhật
            existingChild.setName(updatedChild.getName());
            existingChild.setGender(updatedChild.getGender());
            existingChild.setDateOfBirth(updatedChild.getDateOfBirth());
            existingChild.setLastCheckup(updatedChild.getLastCheckup());

            childService.saveChild(existingChild);
            redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin trẻ thành công!");
        }

        return "redirect:/member/manage";
    }

    // Xử lý xoá trẻ (nếu cần)
    @PostMapping("/child/{id}/delete")
    public String deleteChild(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        childService.deleteChild(id);
        redirectAttributes.addFlashAttribute("message", "Xoá trẻ thành công!");
        return "redirect:/member/manage";
    }
    /*
     Bạn cần tạo thêm các file HTML sau:
        edit-child.html (form cập nhật)

        view-child.html (xem chi tiết)

        Sửa dashboard.html để thêm liên kết đến edit, view, delete nếu muốn.
    */
//    // Hiển thị form chỉnh sửa
//    @GetMapping("/doctor/{id}/edit")
//    public String showEditDoctorForm(@PathVariable Long id, Model model) {
//        User doctor = doctorService.getDoctorById(id);
//        model.addAttribute("doctor", doctor);
//        return "/admin/doctor/edit-doctor";
//    }
//
//    // Xử lý cập nhật
//    @PostMapping("/doctor/{id}/edit")
//    public String updateDoctor(@PathVariable Long id, @ModelAttribute("doctor") User updatedDoctor) {
//        doctorService.updateDoctor(id, updatedDoctor);
//        return "redirect:/admin/doctor/manage";
//    }
//
//    // Xử lý xoá bác sĩ
//    @PostMapping("/doctor/{id}/delete")
//    public String deleteDoctor(@PathVariable Long id) {
//        doctorService.deleteDoctor(id);
//        return "redirect:/admin/doctor/manage";
//    }
}
