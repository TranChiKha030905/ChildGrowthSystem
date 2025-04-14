
package com.childgrowth.tracking.service.impl;

import com.childgrowth.tracking.model.DoctorApproval;
import com.childgrowth.tracking.service.DoctorApprovalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorApprovalServiceImpl implements DoctorApprovalService {

    @Override
    public List<DoctorApproval> getPendingApprovals() {
        // Trả về danh sách trống (mock)
        return new ArrayList<>();
    }
}
