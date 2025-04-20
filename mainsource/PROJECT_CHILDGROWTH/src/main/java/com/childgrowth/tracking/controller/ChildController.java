package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.exception.ResourceNotFoundException;
import com.childgrowth.tracking.model.*;
import com.childgrowth.tracking.repository.*;
import com.childgrowth.tracking.service.ChildService;
import com.childgrowth.tracking.service.GrowthRecordService;
import com.childgrowth.tracking.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ChildController {

    private final UserService userService;
    private final ChildService childService;
    private final ChildRepository childRepository;
    private final UserRepository userRepository;

    private final AdviceRequestRepository adviceRequestRepository;

    private final MembershipPlanRepository membershipPlanRepository;

    private final ChildProfileRepository childProfileRepository;


    private final GrowthRecordRepository growthRecordRepository;
    private final GrowthRecordService growthRecordService;
//    ----------------------------- đối với member---------------------
    //tạo tài khoảng của trẻ
    @GetMapping("/member/add-child")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("children", new Child());  // truyền object vào model
        return "/member/children/add-child";
    }

    @PostMapping("/member/add-child")
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
    @GetMapping("/member/manage")
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

    // Xử lý xoá trẻ
    @PostMapping("/member/child/{id}/delete")
    public String deleteChild(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        childService.deleteChild(id);
        redirectAttributes.addFlashAttribute("message", "Xoá trẻ thành công!");
        return "redirect:/member/manage";
    }

    // Hiển thị chi tiết trẻ (bao gồm bảng tăng trưởng)
    @GetMapping("/member/child/{id}")
    public String viewChildDetailPage(@PathVariable Long id,
                                      Model model,
                                      RedirectAttributes redirectAttributes) {
        Optional<Child> childOptional = childRepository.findById(id);
        if (childOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy thông tin trẻ.");
            return "redirect:/member/manage";
        }

        Child child = childOptional.get();

        model.addAttribute("child", child);
        model.addAttribute("growthRecords", child.getGrowthRecords());
        return "member/children/child-details";
    }

    // Xử lý gửi yêu cầu tư vấn bác sĩ
    @PostMapping("/member/child/{id}/request-advice")
    public String requestDoctorAdvice(@PathVariable Long id,
                                      @RequestParam("question") String question,
                                      Authentication authentication,
                                      RedirectAttributes redirectAttributes) {


        // 1. Xác thực người dùng
        User currentUser = userService.getUserByUsername(authentication.getName());
        // 3. Kiểm tra gói membership
        if (!currentUser.getIdMembership().getName().equalsIgnoreCase("VIP")) {
            redirectAttributes.addFlashAttribute("error", "Chỉ thành viên gói VIP mới có thể yêu cầu tư vấn bác sĩ");
            return "redirect:/member/child/" + id;
        }
        // 2. Kiểm tra tồn tại trẻ
        Optional<Child> childOptional = childRepository.findById(id);
        if (childOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy trẻ");
            return "redirect:/member/manage";
        }
        Child child = childOptional.get();
        // 2. Kiểm tra đã có hồ sơ chưa
        if (child.getChildProfile() == null) {
            redirectAttributes.addFlashAttribute("error", "Trẻ chưa có hồ sơ");
            return "redirect:/member/child/" + id ;
        }


        AdviceRequest advice = AdviceRequest.builder()
                .message(question)
                .child(child.getChildProfile())
                .createdAt(LocalDate.now())
                .resolved(false)
                .build();

        adviceRequestRepository.save(advice);

        redirectAttributes.addFlashAttribute("message", "Yêu cầu tư vấn đã được gửi đến bác sĩ!");
        return "redirect:/member/child/" + id ;
    }




    @GetMapping("/member/child/{id}/advice-requests")
    public String getAdviceRequests(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<ChildProfile> childProfileOptional = childProfileRepository.findById(id);

        if (childProfileOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy hồ sơ trẻ.");
            return "redirect:/member/manage";
        }

        List<AdviceRequest> adviceRequests = adviceRequestRepository.findByChildId(id);

        model.addAttribute("adviceRequests", adviceRequests);
        model.addAttribute("child", childProfileOptional.get());

        return "member/advice-requests"; // tên view
    }

    @GetMapping("/member/child/request-advice")
    public String viewAdviceRequests(Model model, Principal principal) {
        Optional<User> member = userRepository.findByUsername(principal.getName());
        if (member.isEmpty()) return "redirect:/member/dashboard";

        List<AdviceRequest> requests = adviceRequestRepository.findByChild_User_Id(member.get().getId());

        model.addAttribute("requests", requests);
        return "member/children/advice-requests";
    }

    //----------tạo child profile
    @PostMapping("/createChildProfile")
    public String createChildProfile(@RequestParam Long childId,
                                     Authentication authentication,
                                     RedirectAttributes redirectAttributes) {
        User parent = userService.getUserByUsername(authentication.getName());
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy trẻ"));

        // Kiểm tra nếu đã có profile
        if (child.getChildProfile() != null) {
            redirectAttributes.addFlashAttribute("error", "Trẻ đã có hồ sơ");
            return "redirect:/member/manage-childProfile";
        }

        ChildProfile childProfile = ChildProfile.builder()
                .child(child)
                .user(parent)
                .birthDate(child.getDateOfBirth())
                .gender(child.getGender())
                .build();

        childProfileRepository.save(childProfile);

        // Cập nhật quan hệ hai chiều
        child.setChildProfile(childProfile);
        childRepository.save(child);

        redirectAttributes.addFlashAttribute("message", "Tạo hồ sơ thành công");
        return "redirect:/member/manage-childProfile";
    }

//    ---------------- growth records cho trẻ
// Hiển thị trang chỉ số tăng trưởng
@GetMapping("/member/child/{childId}/records")
public String showGrowthRecords(@PathVariable Long childId,
                                Authentication authentication,
                                Model model) {

    User currentUser = userService.getUserByUsername(authentication.getName());
    Child child = childRepository.findByIdAndParent(childId, currentUser)
            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy trẻ"));

    // Lấy danh sách records và thêm vào model
    List<GrowthRecord> records = growthRecordService.getGrowthRecordsByChild(child);
    model.addAttribute("child", child);
    model.addAttribute("records", records);

    // Thêm đối tượng rỗng cho form thêm mới
    model.addAttribute("newRecord", new GrowthRecord());

    return "member/children/growth-records";
}

    // Xử lý thêm chỉ số mới
    @PostMapping("/member/child/{childId}/records")
    public String addGrowthRecord(@PathVariable Long childId,
                                  @ModelAttribute("newRecord") GrowthRecord newRecord,
                                  BindingResult result,
                                  Authentication authentication,
                                  RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newRecord", result);
            redirectAttributes.addFlashAttribute("newRecord", newRecord);
            return "redirect:/member/child/" + childId + "/records";
        }

        User currentUser = userService.getUserByUsername(authentication.getName());
        Child child = childRepository.findByIdAndParent(childId, currentUser)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy trẻ"));

        newRecord.setChild(child);
        newRecord.setMeasurementDate(LocalDateTime.now());
        newRecord.setMeasuredBy(currentUser.getFullName());

        growthRecordService.saveGrowthRecord(newRecord);

        redirectAttributes.addFlashAttribute("success", "Đã thêm chỉ số mới thành công!");
        return "redirect:/member/child/" + childId + "/records";
    }


}
