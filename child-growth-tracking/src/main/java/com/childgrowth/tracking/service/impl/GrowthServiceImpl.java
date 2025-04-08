package com.childgrowth.tracking.service.impl;

import com.childgrowth.tracking.model.ChildProfile;
import com.childgrowth.tracking.model.GrowthRecord;
import com.childgrowth.tracking.repository.GrowthRecordRepository;
import com.childgrowth.tracking.service.GrowthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrowthServiceImpl implements GrowthService {

    @Autowired
    private GrowthRecordRepository growthRecordRepository;

    @Override
    public void saveRecord(GrowthRecord record) {
        growthRecordRepository.save(record);
    }

    @Override
    public List<GrowthRecord> getRecordsByChild(ChildProfile child) {
        return growthRecordRepository.findByChild(child);
    }
}
