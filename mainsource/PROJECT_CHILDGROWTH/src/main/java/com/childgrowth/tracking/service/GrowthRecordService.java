package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.GrowthRecord;
import com.childgrowth.tracking.model.Child;
import java.time.LocalDateTime;
import java.util.List;

public interface GrowthRecordService {
    GrowthRecord saveGrowthRecord(GrowthRecord record);
    GrowthRecord getGrowthRecordById(Long id);
    List<GrowthRecord> getGrowthRecordsByChild(Child child);
    List<GrowthRecord> getGrowthRecordsByChildAndDateRange(Child child, LocalDateTime startDate, LocalDateTime endDate);
    List<GrowthRecord> getVerifiedGrowthRecordsByChild(Child child);
    GrowthRecord getLatestGrowthRecordByChild(Child child);
    void updateGrowthRecord(GrowthRecord record);
    void deleteGrowthRecord(Long id);
    void verifyGrowthRecord(Long id, String verificationNotes);
    void calculateGrowthMetrics(GrowthRecord record);
    void checkGrowthAlerts(GrowthRecord record);
} 