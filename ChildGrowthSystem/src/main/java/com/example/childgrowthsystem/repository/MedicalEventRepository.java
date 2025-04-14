package com.example.childgrowthsystem.repository;

import com.childgrowth.tracking.model.MedicalEvent;
import com.childgrowth.tracking.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicalEventRepository extends JpaRepository<MedicalEvent, Long> {
    List<MedicalEvent> findByChild(Child child);
    List<MedicalEvent> findByChildOrderByEventDateDesc(Child child);
    List<MedicalEvent> findByChildAndEventDateBetween(Child child, LocalDateTime startDate, LocalDateTime endDate);
    List<MedicalEvent> findByChildAndEventType(Child child, String eventType);
    List<MedicalEvent> findByChildAndIsResolved(Child child, boolean isResolved);
    List<MedicalEvent> findByChildAndMilestoneCategory(Child child, String milestoneCategory);
} 