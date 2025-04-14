package com.example.childgrowthsystem.repository;

import com.childgrowth.tracking.model.ActivityRecord;
import com.childgrowth.tracking.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityRecordRepository extends JpaRepository<ActivityRecord, Long> {
    List<com.example.childgrowthsystem.repository.ActivityRecord> findByChild(Child child);
    List<ActivityRecord> findByChildOrderByActivityDateDesc(Child child);
    List<ActivityRecord> findByChildAndActivityDateBetween(Child child, LocalDateTime startDate, LocalDateTime endDate);
    List<ActivityRecord> findByChildAndActivityType(Child child, String activityType);
    List<ActivityRecord> findByChildAndSkillCategory(Child child, String skillCategory);
    List<ActivityRecord> findByChildAndSkillLevelGreaterThanEqual(Child child, Integer minSkillLevel);
    Double findAverageDurationByChildAndActivityDateBetween(Child child, LocalDateTime startDate, LocalDateTime endDate);
} 