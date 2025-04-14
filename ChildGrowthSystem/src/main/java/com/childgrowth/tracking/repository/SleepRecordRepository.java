package com.childgrowth.tracking.repository;

import com.childgrowth.tracking.model.SleepRecord;
import com.childgrowth.tracking.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SleepRecordRepository extends JpaRepository<SleepRecord, Long> {
    List<SleepRecord> findByChild(Child child);
    List<SleepRecord> findByChildOrderBySleepStartDesc(Child child);
    List<SleepRecord> findByChildAndSleepStartBetween(Child child, LocalDateTime startDate, LocalDateTime endDate);
    List<SleepRecord> findByChildAndSleepLocation(Child child, String location);
    List<SleepRecord> findByChildAndSleepQualityGreaterThanEqual(Child child, Integer minQuality);
    Double findAverageDurationByChildAndSleepStartBetween(Child child, LocalDateTime startDate, LocalDateTime endDate);
} 