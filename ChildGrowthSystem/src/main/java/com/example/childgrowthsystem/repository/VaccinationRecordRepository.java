package com.example.childgrowthsystem.repository;

import com.childgrowth.tracking.model.VaccinationRecord;
import com.childgrowth.tracking.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Long> {
    List<VaccinationRecord> findByChild(Child child);
    List<VaccinationRecord> findByChildOrderByVaccinationDateDesc(Child child);
    List<VaccinationRecord> findByChildAndVaccinationDateBetween(Child child, LocalDateTime startDate, LocalDateTime endDate);
    List<VaccinationRecord> findByChildAndVaccineType(Child child, String vaccineType);
    List<VaccinationRecord> findByChildAndIsScheduled(Child child, boolean isScheduled);
    List<VaccinationRecord> findByNextDueDateBefore(LocalDateTime date);
    List<VaccinationRecord> findByChildAndHadReaction(Child child, boolean hadReaction);
} 