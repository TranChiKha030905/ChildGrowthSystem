package com.example.childgrowth.service;

import com.example.childgrowth.model.*;
import com.example.childgrowth.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildService {

    @Autowired
    private ChildProfileRepository childProfileRepository; // Updated to ChildProfileRepository

    @Autowired
    private GrowthMilestoneRepository growthMilestoneRepository;

    @Autowired
    private VaccinationRepository vaccinationRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public List<ChildProfile> getAllChildren() {
        return childProfileRepository.findAll();
    }

    public ChildProfile saveChild(ChildProfile child) {
        double heightInMeters = child.getHeightCm() / 100;
        double bmi = child.getWeightKg() / (heightInMeters * heightInMeters);
        child.setBmi(bmi);
        return childProfileRepository.save(child);
    }

    public void deleteChild(Long id) {
        childProfileRepository.deleteById(id);
    }

    public double getAverageBmi() {
        List<ChildProfile> children = childProfileRepository.findAll();
        if (children.isEmpty()) return 0.0;
        return children.stream().mapToDouble(ChildProfile::getBmi).average().orElse(0.0);
    }

    // Thêm mốc phát triển
    public GrowthMilestone addGrowthMilestone(GrowthMilestone milestone) {
        return growthMilestoneRepository.save(milestone);
    }

    // Thêm lịch tiêm chủng
    public Vaccination addVaccination(Vaccination vaccination) {
        return vaccinationRepository.save(vaccination);
    }

    // Gửi thông báo
    public Notification sendNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // Lấy thông báo chưa đọc
    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndIsReadFalse(userId);
    }
}