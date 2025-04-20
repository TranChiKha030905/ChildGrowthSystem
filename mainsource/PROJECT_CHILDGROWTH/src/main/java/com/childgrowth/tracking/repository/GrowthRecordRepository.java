// GrowthRecordRepository.java
package com.childgrowth.tracking.repository;

import com.childgrowth.tracking.model.GrowthRecord;
import com.childgrowth.tracking.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GrowthRecordRepository extends JpaRepository<GrowthRecord, Long> {
    List<GrowthRecord> findByChild(Child child);
    List<GrowthRecord> findByChildOrderByMeasurementDateDesc(Child child);
    List<GrowthRecord> findByChildAndMeasurementDateBetween(Child child, LocalDateTime startDate, LocalDateTime endDate);
    List<GrowthRecord> findByChildAndIsVerified(Child child, boolean isVerified);
    GrowthRecord findTopByChildOrderByMeasurementDateDesc(Child child);
    List<GrowthRecord> findByChildIdOrderByMeasurementDateAsc(Long childId);

    // Tìm các bản ghi theo Child và sắp xếp theo measurementDate
    List<GrowthRecord> findByChildOrderByMeasurementDateAsc(Child child);
}