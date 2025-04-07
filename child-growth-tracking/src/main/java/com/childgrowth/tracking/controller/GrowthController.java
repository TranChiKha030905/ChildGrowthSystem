package com.childgrowth.tracking.controller;

import com.childgrowth.tracking.model.ChildProfile;
import com.childgrowth.tracking.model.GrowthRecord;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.service.ChildProfileService;
import com.childgrowth.tracking.service.GrowthService;
import com.childgrowth.tracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/growth")
public class GrowthController {

    @Autowired
    private GrowthService growthService;

    @Autowired
    private ChildProfileService childService;

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String showForm(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUsername(currentUser.getUsername());
        List<ChildProfile> children = childService.getChildrenByUser(user);
        model.addAttribute("children", children);
        model.addAttribute("record", new GrowthRecord()); // CHÍNH LÀ "record"
        return "growth/add";
    }

    @PostMapping("/add")
    public String saveRecord(@ModelAttribute("record") GrowthRecord record) {
        growthService.saveRecord(record);
        return "redirect:/dashboard"; // hoặc trang biểu đồ
    }
    @GetMapping("/child/{id}/records")
    public String viewRecords(@PathVariable("id") Long childId,
                              Model model,
                              @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUsername(currentUser.getUsername());
        ChildProfile child = childService.getChildrenByUser(user).stream()
                .filter(c -> c.getId().equals(childId))
                .findFirst()
                .orElse(null);

        if (child == null) {
            return "redirect:/children"; // không thấy trẻ hoặc không thuộc user
        }

        model.addAttribute("child", child);
        model.addAttribute("records", growthService.getRecordsByChild(child));
        return "growth/records";
    }

}
