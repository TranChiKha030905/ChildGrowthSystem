package com.childgrowth.tracking.repository;

import com.childgrowth.tracking.model.ChildProfile;
import com.childgrowth.tracking.model.GrowthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GrowthRecordRepository extends JpaRepository<GrowthRecord, Long> {
    List<GrowthRecord> findByChild(ChildProfile child);
}
