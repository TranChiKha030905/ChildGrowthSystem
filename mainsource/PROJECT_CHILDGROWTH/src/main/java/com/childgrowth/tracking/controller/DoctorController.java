
package com.childgrowth.tracking.controller;


import com.childgrowth.tracking.service.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.childgrowth.tracking.exception.ResourceNotFoundException;
import com.childgrowth.tracking.model.*;
import com.childgrowth.tracking.repository.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final UserService userService;
    private final ChildProfileRepository childProfileRepository;
    private final UserRepository userRepository;
    private final AdviceRequestRepository adviceRequestRepository;

    private final ChildService childService;
    private final ChildRepository childRepository;
    private final GrowthRecordService growthRecordService;


    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        try {
            List<ChildProfile> patients = childProfileRepository.findAll();
            List<AdviceRequest> requests = adviceRequestRepository.findByResolvedFalse();
            model.addAttribute("requests", requests);
            model.addAttribute("patients", patients);
            System.out.println("Số bệnh nhân: " + patients.size());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách bệnh nhân");
            e.printStackTrace();
        }
        return "/doctor/dashboard";
    }

    // Trang danh sách yêu cầu
    @GetMapping("/advice-requests")
    public String getAdviceRequests(Model model) {
        List<AdviceRequest> requests = adviceRequestRepository.findByResolvedFalse();
        model.addAttribute("requests", requests);
        return "/doctor/advice-requests";
    }

    // Trang nhập phản hồi cho từng yêu cầu
    @GetMapping("/advice-requests/{id}/response-form")
    public String showResponseForm(@PathVariable Long id, Model model) {
        AdviceRequest request = adviceRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Yêu cầu không tồn tại"));
        model.addAttribute("request", request);
        return "doctor/advice-request-response";
    }

    // Xử lý phản hồi
    @PostMapping("/advice-requests/{id}/response")
    public String respondToAdviceRequest(@PathVariable Long id,
                                         @RequestParam("response") String responseText) {
        adviceRequestRepository.findById(id).ifPresent(adviceRequest -> {
            adviceRequest.setResponse(responseText);
            adviceRequest.setResolved(true);
            adviceRequestRepository.save(adviceRequest);
        });
        return "redirect:/doctor/advice-requests";
    }

    //--------------------------quản lí bệnh nhân

    @GetMapping("/patients/details/{id}")
    public String showPatientDetails(@PathVariable Long id, Model model, Authentication authentication) {
        String currentUsername = authentication.getName();
        User currentDoctor = userService.getUserByUsername(currentUsername);


        try {
            ChildProfile patient = childProfileRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bệnh nhân với ID: " + id));

            // Kiểm tra và xử lý nếu birthDate null
            if (patient.getBirthDate() == null) {
                patient.setBirthDate(LocalDate.now());
            }

            model.addAttribute("patient", patient);
            return "doctor/patient-manage/patient-details";

        } catch (ResourceNotFoundException e) {
            // Xử lý khi không tìm thấy bệnh nhân
            return "redirect:/doctor/patients?error=not_found";
        } catch (Exception e) {
            // Xử lý các lỗi khác
            return "redirect:/doctor/patients?error=server_error";
        }
    }

    // Danh sách bệnh nhân
    @GetMapping("/patients")
    public String listAllPatients(Model model) {
        try {
            List<ChildProfile> patients = childProfileRepository.findAll();
            model.addAttribute("patients", patients);
            System.out.println("Số bệnh nhân: " + patients.size());
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách bệnh nhân");
            e.printStackTrace();
        }
        return "doctor/patient-manage/patient-list";
    }


    // Form thêm bệnh nhân và xử lý các thay đổi
    @GetMapping("/patients/add")
    public String showAddForm(Model model) {
        model.addAttribute("patient", new ChildProfile());
        model.addAttribute("minDate", LocalDate.now().minusYears(18));
        model.addAttribute("maxDate", LocalDate.now());

        // Lấy danh sách người nhà
        try {
            List<User> users = userService.getAllMembers();
            model.addAttribute("users", users);
        } catch (Exception e) {
            model.addAttribute("users", new ArrayList<User>());
            model.addAttribute("error", "Không thể tải danh sách người nhà.");
        }

        return "doctor/patient-manage/patient-form";
    }

    @PostMapping("/patients/add")
    public String addPatient(@ModelAttribute("patient") @Valid ChildProfile patient,
                             BindingResult result, Model model,
                             Authentication authentication) {
        // Validate và xử lý logic
        if (result.hasErrors()) {
            model.addAttribute("users", userService.getAllMembers());
            model.addAttribute("minDate", LocalDate.now().minusYears(18));
            model.addAttribute("maxDate", LocalDate.now());
            return "doctor/patient-manage/patient-form";
        }

        // Lấy thông tin bác sĩ hiện tại từ Authentication
        String currentUsername = authentication.getName();
        User currentDoctor = userService.getUserByUsername(currentUsername);

        // Tạo mới Child từ thông tin nhập
        Child child = new Child();
        child.setName(patient.getName());
        child.setDateOfBirth(patient.getBirthDate());
        child.setGender(patient.getGender());
        child.setDoctor(currentDoctor);
        //lần cuối được khám
        child.setLastCheckup(LocalDate.now());

        // Liên kết với User (người nhà)
        if (patient.getUser() != null && patient.getUser().getId() != null) {
            User user = userRepository.findById(patient.getUser().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user Id"));
            child.setParent(user);
        }

        // Lưu Child
        child = childRepository.save(child);

        // Thiết lập quan hệ cho ChildProfile
        patient.setChild(child);

        // Lưu ChildProfile
        childProfileRepository.save(patient);

        return "redirect:/doctor/patients";
    }

    // Form chỉnh sửa bệnh nhân
    @GetMapping("/patients/edit/{id}")
    public String showEditFormMem(@PathVariable Long id, Model model) {
        ChildProfile patient = childProfileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID bệnh nhân không hợp lệ: " + id));

        model.addAttribute("patient", patient);
        model.addAttribute("users", userService.getAllMembers());
        model.addAttribute("minDate", LocalDate.now().minusYears(18));
        model.addAttribute("maxDate", LocalDate.now());

        return "doctor/patient-manage/patient-form";
    }


    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            // Kiểm tra tồn tại trước khi xóa
            ChildProfile patient = childProfileRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy bệnh nhân với ID: " + id));

            // Xóa Child liên kết nếu cần (tùy thuộc vào yêu cầu nghiệp vụ)
            if (patient.getChild() != null) {
                childRepository.delete(patient.getChild());
            }

            childProfileRepository.delete(patient);

            redirectAttributes.addFlashAttribute("success", "Xóa bệnh nhân thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Xóa bệnh nhân thất bại: " + e.getMessage());
            e.printStackTrace(); // Ghi log lỗi
        }
        return "redirect:/doctor/patients";
    }

    //    --------------------------------------Doctor quản lí gói thành viên
    private final MembershipPlanService membershipPlanService;


    // Hiển thị danh sách gói thành viên
    @GetMapping("/membership-plans")
    public String listMembershipPlans(Model model) {
        List<MembershipPlan> plans = membershipPlanService.getAllMembershipPlans();
        model.addAttribute("plans", plans);
        return "doctor/membership/membership-list";
    }

    // Hiển thị form thêm mới
    @GetMapping("/membership-plans/add")
    public String showAddFormMembership(Model model) {
        model.addAttribute("plan", new MembershipPlan());
        return "doctor/membership/membership-form";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/membership-plans/edit/{id}")
    public String showEditFormMembershipPlan(@PathVariable Long id, Model model) {
        MembershipPlan plan = membershipPlanService.getMembershipPlanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid plan Id:" + id));
        model.addAttribute("plan", plan);
        return "doctor/membership/membership-form";
    }

    // Xử lý thêm mới hoặc cập nhật
    @PostMapping("/membership-plans/save")
    public String savePlan(@Valid @ModelAttribute("plan") MembershipPlan plan,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "doctor/membership/membership-form";
        }

        membershipPlanService.saveMembershipPlan(plan);
        redirectAttributes.addFlashAttribute("success", "Lưu gói thành viên thành công!");
        return "redirect:/doctor/membership-plans";
    }

    // Xóa gói thành viên
    @GetMapping("/membership-plans/delete/{id}")
    public String deletePlan(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            membershipPlanService.deleteMembershipPlan(id);
            redirectAttributes.addFlashAttribute("success", "Xóa gói thành viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Xóa gói thành viên thất bại: " + e.getMessage());
        }
        return "redirect:/doctor/membership-plans";
    }


//    --------------------------------FeedBack

    private final FeedbackService feedbackService;

    // Xem danh sách feedback
    @GetMapping("/feedbacks")
    public String getAllFeedbacks(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String type,
            Model model,
            Authentication authentication) {

        // Phân trang và sắp xếp
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(direction, sortParams[0]));

        // Lọc theo status và type nếu có
        Page<Feedback> feedbackPage;
        if (status != null && !status.isEmpty() && type != null && !type.isEmpty()) {
            feedbackPage = feedbackService.findByStatusAndType(status, type, pageable);
        } else if (status != null && !status.isEmpty()) {
            feedbackPage = feedbackService.findByStatus(status, pageable);
        } else if (type != null && !type.isEmpty()) {
            feedbackPage = feedbackService.findByType(type, pageable);
        } else {
            feedbackPage = feedbackService.findAll(pageable);
        }

        model.addAttribute("feedbacks", feedbackPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", feedbackPage.getTotalPages());
        model.addAttribute("size", size); // Thêm size vào model
        model.addAttribute("sort", sort);
        model.addAttribute("status", status);
        model.addAttribute("type", type);

        return "doctor/feedback/feedback-list"; // Đảm bảo template này tồn tại
    }

    // Xem chi tiết feedback
    @GetMapping("/feedbacks/{id}")
    public String getFeedbackDetails(@PathVariable Long id, Model model) {
        Optional<Feedback> feedback = feedbackService.findById(id);
        if (feedback.isEmpty()) {
            return "redirect:/doctor/feedbacks?error=not_found";
        }

        model.addAttribute("feedback", feedback.get());
        return "doctor/feedback/feedback-detail";
    }

    // Phản hồi feedback
    @PostMapping("/feedbacks/{id}/respond")
    public String respondToFeedback(
            @PathVariable Long id,
            @RequestParam String response,
            @RequestParam(required = false) String newStatus,
            Authentication authentication) {

        String currentUsername = authentication.getName();
        User currentDoctor = userService.getUserByUsername(currentUsername);

        feedbackService.respondToFeedback(id, response, newStatus, currentDoctor);
        return "redirect:/doctor/feedbacks/" + id + "?success=responded";
    }

    // Thay đổi trạng thái feedback
    @PostMapping("/feedbacks/{id}/status")
    public String updateFeedbackStatus(
            @PathVariable Long id,
            @RequestParam String status,
            Authentication authentication) {

        String currentUsername = authentication.getName();
        User currentDoctor = userService.getUserByUsername(currentUsername);

        feedbackService.updateFeedbackStatus(id, status, currentDoctor);
        return "redirect:/doctor/feedbacks/" + id + "?success=status_updated";
    }

    //    ----------------- Khámm bệnh cho trẻ------------
// Hiển thị form khám bệnh
    @GetMapping("/patients/examine/{id}")
    public String showExaminationForm(@PathVariable Long id, Model model) {
        ChildProfile profile = childProfileRepository.findById(id).orElse(null);
        if (profile == null || profile.getChild() == null) {
            model.addAttribute("error", "Không tìm thấy bệnh nhân");
            return "redirect:/doctor/patients";
        }
        model.addAttribute("profile", profile); // truyền cả childProfile
        model.addAttribute("child", profile.getChild()); // truyền riêng child
        return "doctor/patient-manage/examination-form";
    }


    // Xử lý lưu khám bệnh
    @PostMapping("/patients/examine/{id}")
    public String saveExamination(@PathVariable Long id,
                                  @RequestParam String diagnosis,
                                  @RequestParam String treatment,
                                  Authentication authentication,
                                  RedirectAttributes redirectAttributes) {
        User doctor = userService.getUserByUsername(authentication.getName());
        ChildProfile profile = childProfileRepository.findById(id).orElse(null);
        if (profile != null && profile.getChild() != null) {
            Child child = profile.getChild();
            child.setDiagnosis(diagnosis);
            child.setTreatment(treatment);
            child.setLastCheckup(LocalDate.now());
            child.setDoctor(doctor);
            childRepository.save(child);
            redirectAttributes.addFlashAttribute("success", "Khám bệnh thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy hồ sơ bệnh nhi");
        }
        return "redirect:/doctor/patients";
    }


//---------------------------xem biểu đồ tăng trưởng của trẻ
// GET: Xem biểu đồ tăng trưởng
@GetMapping("/child/{profileId}/growth")
public String viewGrowthChart(@PathVariable Long profileId, Model model) {
    ChildProfile profile = childProfileRepository.findById(profileId)
            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy hồ sơ trẻ với ID: " + profileId));

    Child child = profile.getChild();
    List<GrowthRecord> records = growthRecordService.findByChildIdOrderByMeasurementDateAsc(child.getId());

    model.addAttribute("child", child);
    model.addAttribute("records", records);

    return "doctor/patient-manage/growth_records"; // Đảm bảo file HTML tên như này
}

}
