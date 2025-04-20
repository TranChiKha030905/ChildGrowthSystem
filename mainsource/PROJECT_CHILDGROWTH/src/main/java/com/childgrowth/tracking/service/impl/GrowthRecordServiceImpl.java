package com.childgrowth.tracking.service.impl;

import com.childgrowth.tracking.model.GrowthRecord;
import com.childgrowth.tracking.model.Child;
import com.childgrowth.tracking.repository.GrowthRecordRepository;
import com.childgrowth.tracking.service.GrowthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GrowthRecordServiceImpl implements GrowthRecordService {

    @Autowired
    private GrowthRecordRepository growthRecordRepository;

    @Override
    public GrowthRecord saveGrowthRecord(GrowthRecord record) {
        // Tính BMI đơn giản
        if (record.getHeight() != null && record.getWeight() != null) {
            double heightInMeters = record.getHeight() / 100;
            double bmi = record.getWeight() / (heightInMeters * heightInMeters);
            record.setBmi(Math.round(bmi * 10) / 10.0); // Làm tròn 1 số thập phân
        }

        return growthRecordRepository.save(record);
    }

    @Override
    public GrowthRecord getGrowthRecordById(Long id) {
        return growthRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Growth record not found"));
    }

    @Override
    public List<GrowthRecord> getGrowthRecordsByChild(Child child) {
        return growthRecordRepository.findByChildOrderByMeasurementDateAsc(child);
    }

    @Override
    public List<GrowthRecord> getGrowthRecordsByChildAndDateRange(Child child, LocalDateTime startDate, LocalDateTime endDate) {
        return growthRecordRepository.findByChildAndMeasurementDateBetween(child, startDate, endDate);
    }

    @Override
    public List<GrowthRecord> getVerifiedGrowthRecordsByChild(Child child) {
        return growthRecordRepository.findByChildAndIsVerified(child, true);
    }

    @Override
    public GrowthRecord getLatestGrowthRecordByChild(Child child) {
        return growthRecordRepository.findTopByChildOrderByMeasurementDateDesc(child);
    }

    @Override
    @Transactional
    public void updateGrowthRecord(GrowthRecord record) {
        growthRecordRepository.save(record);
    }

    @Override
    @Transactional
    public void deleteGrowthRecord(Long id) {
        growthRecordRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void verifyGrowthRecord(Long id, String verificationNotes) {
        GrowthRecord record = getGrowthRecordById(id);
        record.setVerified(true);
        record.setVerificationNotes(verificationNotes);
        growthRecordRepository.save(record);
    }

    @Override
    public void calculateGrowthMetrics(GrowthRecord record) {
        // Implement growth metrics calculation logic
    }

    @Override
    public void checkGrowthAlerts(GrowthRecord record) {
        // Implement growth alerts checking logic
    }
} 