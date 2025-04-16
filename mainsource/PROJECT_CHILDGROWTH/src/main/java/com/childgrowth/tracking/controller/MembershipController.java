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

    private static final Logger logger = LoggerFactory.getLogger(MembershipController.class);

    @Autowired
    private MembershipPlanRepository membershipPlanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/memberships")
    public String showMemberships(Model model) {

        //Lấy thông tin người dùng từ Spring Security (Authentication)
        //Kiểm tra xem principal có phải là UserDetails không
        //Chuyển đổi principal thành UserDetails:
        //Lấy thông tin người dùng từ cơ sở dữ liệu:
        //Lấy ID của gói thành viên (nếu có):
        //Nếu không tìm thấy người dùng hoặc có lỗi, trả về null:
//        Lưu currentMembershipId vào model:
        //Ghi log thông tin của currentMembershipId:
        try {
            Long currentMembershipId = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .filter(principal -> principal instanceof UserDetails)
                    .map(principal -> (UserDetails) principal)
                    .flatMap(userDetails -> {
                        logger.debug("Fetching user by username: {}", userDetails.getUsername());
                        return userRepository.findByUsername(userDetails.getUsername());
                    })
                    .map(user -> user.getIdMembership() != null ? user.getIdMembership().getId() : null)
                    .orElse(null);

            logger.info("Current membership ID: {}", currentMembershipId);
            model.addAttribute("currentMembershipId", currentMembershipId);
        } catch (Exception e) {
            logger.error("Error rendering memberships page", e);
            model.addAttribute("error", "Đã xảy ra lỗi khi tải trang gói thành viên.");
        }
        return "memberships";
    }

    @PostMapping("/purchase-membership")
    public String purchaseMembership(@RequestParam("planId") Long planId, RedirectAttributes redirectAttributes) {

        // Nếu user đã mua gói này rồi thì không xử lý nữa
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Kiểm tra nếu người dùng chưa đăng nhập
        if (authentication == null || !authentication.isAuthenticated() ||
                authentication.getPrincipal() == null ||
                authentication.getPrincipal() instanceof String && "anonymousUser".equals(authentication.getPrincipal())) {
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập
        }
        try {
            Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .filter(principal -> principal instanceof UserDetails)
                    .map(principal -> (UserDetails) principal)
                    .flatMap(userDetails -> {
                        logger.debug("Fetching user by username: {}", userDetails.getUsername());
                        return userRepository.findByUsername(userDetails.getUsername());
                    })
                    .ifPresent(user -> {
                        logger.debug("Processing purchase for planId: {}", planId);
                        MembershipPlan plan = membershipPlanRepository.findById(planId).orElse(null);
                        if (plan == null) {
                            redirectAttributes.addFlashAttribute("error", "Gói không tồn tại");
                        }
                        else if (user.getIdMembership() != null && user.getIdMembership().getId().equals(planId)) { // Kiểm tra xem gói đã mua chưa
                            redirectAttributes.addFlashAttribute("error", "Bạn đã mua gói này rồi.");
                        }
                        else if (plan.getPrice() > 0 && (user.getMoney() == null || user.getMoney() < plan.getPrice())) {
                            redirectAttributes.addFlashAttribute("error", "Số dư không đủ");
                        } else {
                            user.setIdMembership(plan);
                            if (plan.getPrice() > 0) {
                                user.setMoney(user.getMoney() - plan.getPrice());
                            }
                            userService.saveMember(user);
                            redirectAttributes.addFlashAttribute("success", "Mua gói thành công");
                        }
                    });
        } catch (Exception e) {
            logger.error("Error processing purchase for planId: {}", planId, e);
            redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi mua gói.");
        }
        return "redirect:/memberships";
    }
}