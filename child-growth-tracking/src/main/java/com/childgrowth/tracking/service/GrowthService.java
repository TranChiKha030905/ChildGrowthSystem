package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.ChildProfile;
import com.childgrowth.tracking.model.GrowthRecord;
import com.childgrowth.tracking.repository.GrowthRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrowthService {

    @Autowired
    private GrowthRecordRepository growthRepo;

    public void saveRecord(GrowthRecord record) {
        growthRepo.save(record);
    }

    public List<GrowthRecord> getRecordsByChild(ChildProfile child) {
        return growthRepo.findByChild(child);
    }
}
