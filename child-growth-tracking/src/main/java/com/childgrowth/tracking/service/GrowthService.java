package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.ChildProfile;
import com.childgrowth.tracking.model.GrowthRecord;

import java.util.List;

public interface GrowthService {
    void saveRecord(GrowthRecord record);
    List<GrowthRecord> getRecordsByChild(ChildProfile child);
}
