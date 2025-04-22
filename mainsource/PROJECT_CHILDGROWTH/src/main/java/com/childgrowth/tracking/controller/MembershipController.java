package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.model.MembershipPlan;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.repository.MembershipPlanRepository;
import com.childgrowth.tracking.repository.UserRepository;
import com.childgrowth.tracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Controller
public class MembershipController {

    @Autowired
    private MembershipPlanRepository membershipPlanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

// -------------------------------------------Function-----------------------------------------------------
    //kiểm tra người dùng đăng nhập chưa

    //Lấy thông tin người dùng từ Spring Security (Authentication)
    //Kiểm tra xem principal có phải là UserDetails không
    //Chuyển đổi principal thành UserDetails:
    //Lấy thông tin người dùng từ cơ sở dữ liệu:
    //Lấy ID của gói thành viên (nếu có):
    //Nếu không tìm thấy người dùng hoặc có lỗi, trả về null:
    //Lưu currentMembershipId vào model:
    //Ghi log thông tin của currentMembershipId:

    private Optional<User> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserDetails userDetails) {
            return userRepository.findByUsername(userDetails.getUsername());
        }
        return Optional.empty();
    }


    //kiểm tra gói và số dư
    public String purchasePlan(User user, Long planId) {
        Optional<MembershipPlan> optionalPlan = membershipPlanRepository.findById(planId);
        if (optionalPlan.isEmpty()) return "Gói không tồn tại";

        MembershipPlan plan = optionalPlan.get();

        if (user.getIdMembership() != null && user.getIdMembership().getId().equals(planId)) {
            return "Bạn đã mua gói này rồi.";
        }

        if (plan.getPrice() > 0 && (user.getMoney() == null || user.getMoney() < plan.getPrice())) {
            return "Số dư không đủ";
        }

        user.setIdMembership(plan);
        if (plan.getPrice() > 0) {
            user.setMoney(user.getMoney() - plan.getPrice());
        }

        userService.saveMember(user); // hoặc userRepository.save(user)
        return "success";
    }

//    -----------------------------------------------------------------------------------------------

    //chức năng mua gói
    @GetMapping("/memberships")
    public String showMemberships(Model model) {
        try {
            Optional<User> optionalUser = getCurrentUser();

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                Long currentMembershipId = (user.getIdMembership() != null) ? user.getIdMembership().getId() : null;
                model.addAttribute("currentMembershipId", currentMembershipId);
            }

        } catch (Exception e) {
            model.addAttribute("error", "Đã xảy ra lỗi khi tải trang gói thành viên.");
        }

        return "memberships";
    }



    @PostMapping("/purchase-membership")
    public String purchaseMembership(@RequestParam("planId") Long planId, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = getCurrentUser();

        // Nếu không tìm thấy user (chưa đăng nhập) thì chuyển hướng login
        if (optionalUser.isEmpty()) {
            return "redirect:/login";
        }

        User user = optionalUser.get();
        String result = userService.purchasePlan(user, planId);

        if ("success".equals(result)) {
            redirectAttributes.addFlashAttribute("success", "Mua gói thành công");
        } else {
            redirectAttributes.addFlashAttribute("error", result);
        }

        return "redirect:/memberships";
    }

}