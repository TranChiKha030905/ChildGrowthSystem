
package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.model.*;
import com.childgrowth.tracking.repository.AdviceRequestRepository;
import com.childgrowth.tracking.repository.ChildProfileRepository;
import com.childgrowth.tracking.repository.ChildRepository;
import com.childgrowth.tracking.repository.MembershipPlanRepository;
import com.childgrowth.tracking.service.ChildService;
import com.childgrowth.tracking.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    private final AdviceRequestRepository adviceRequestRepository;

    private final ChildProfileRepository childProfileRepository;
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


//------------------quản lí profile trẻ
//hiện danh sách quản lí hồ sơ trẻ
@GetMapping("/manage-childProfile")
public String showChildProfiles(Model model, Authentication authentication) {
    User currentUser = userService.getUserByUsername(authentication.getName());

    // Lấy tất cả trẻ của user
    List<Child> allChildren = childRepository.findByParent(currentUser);

    // Chia thành 2 danh sách
    List<ChildProfile> existingProfiles = new ArrayList<>();
    List<Child> childrenWithoutProfiles = new ArrayList<>();

    for (Child child : allChildren) {
        if (child.getChildProfile() != null) {
            existingProfiles.add(child.getChildProfile());
        } else {
            childrenWithoutProfiles.add(child);
        }
    }

    model.addAttribute("childProfiles", existingProfiles);
    model.addAttribute("children", childrenWithoutProfiles);

    return "member/children/manage-childProfile";
}


    //--xóa profile của trẻ
    @PostMapping("/deleteChildProfile")
    @Transactional
    public String deleteChildProfile(@RequestParam("id") Long profileId,
                                     Authentication authentication,
                                     RedirectAttributes redirectAttributes) {
        try {
            // Xác thực người dùng
            User currentUser = userService.getUserByUsername(authentication.getName());

            ChildProfile profile = childProfileRepository.findById(profileId)
                    .orElseThrow(() -> {
                        redirectAttributes.addFlashAttribute("error", "Không tìm thấy hồ sơ");
                        return new ResponseStatusException(HttpStatus.NOT_FOUND);
                    });

            // Kiểm tra quyền
            if (!profile.getUser().getId().equals(currentUser.getId())) {
                redirectAttributes.addFlashAttribute("error", "Bạn không có quyền xóa hồ sơ này");
                return "redirect:/member/manage-childProfile";
            }

            // Xử lý quan hệ
            if (profile.getChild() != null) {
                Child child = profile.getChild();
                child.setChildProfile(null);
                childRepository.save(child);
            }

            childProfileRepository.delete(profile);

            redirectAttributes.addFlashAttribute("message", "Đã xóa hồ sơ thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Xóa hồ sơ thất bại: " + e.getMessage());
        }

        return "redirect:/member/manage-childProfile";
    }

//--------------------------------------yêu cầu gửi

    @GetMapping("/add-request/{id}")
    public String showAddRequestForm(@PathVariable("id") Long childId,
                                     Model model,
                                     Authentication authentication,
                                     RedirectAttributes redirectAttributes) {

        User currentUser = userService.getUserByUsername(authentication.getName());

        // Kiểm tra hồ sơ trẻ và quyền sở hữu
        Optional<Child> child = childRepository.findByIdAndParent(childId, currentUser);
        if (child.isEmpty() || child.get().getChildProfile() == null) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy hồ sơ hợp lệ");
            return "redirect:/member/manage-childProfile";
        }

        Child childEntity = child.get();
        ChildProfile profile = childEntity.getChildProfile();

        List<AdviceRequest> requests = adviceRequestRepository.findByChild(profile);

        model.addAttribute("child", childEntity);
        model.addAttribute("requests", requests);
        return "member/children/add-request";
    }

    @PostMapping("/add-request/{id}")
    public String handleAddRequest(@PathVariable("id") Long childId,
                                   @RequestParam("question") String question,
                                   Authentication authentication,
                                   RedirectAttributes redirectAttributes) {

        User currentUser = userService.getUserByUsername(authentication.getName());

        // Kiểm tra gói VIP
        if (!currentUser.getIdMembership().getName().equalsIgnoreCase("VIP")) {
            redirectAttributes.addFlashAttribute("error", "Yêu cầu gói VIP");
            return "redirect:/member/add-request/" + childId;
        }

        // Kiểm tra hồ sơ trẻ
        Child child = childRepository.findByIdAndParent(childId, currentUser).orElse(null);
        if (child == null) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy trẻ.");
            return "redirect:/member/manage-childProfile";
        }

        ChildProfile profile = child.getChildProfile();

        if (profile == null || profile.getId() == null) {
            redirectAttributes.addFlashAttribute("error", "Hồ sơ trẻ chưa được tạo hoặc chưa được lưu.");
            return "redirect:/member/manage-childProfile";
        }


        AdviceRequest advice = AdviceRequest.builder()
                .message(question)
                .child(profile)
                .createdAt(LocalDate.now())
                .resolved(false)
                .build();

        adviceRequestRepository.save(advice);
        redirectAttributes.addFlashAttribute("message", "Yêu cầu đã được gửi!");
        return "redirect:/member/add-request/" + childId;
    }
}
