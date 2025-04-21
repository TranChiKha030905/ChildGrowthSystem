
package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.DoctorApproval;
import java.util.List;

public interface DoctorApprovalService {
    List<DoctorApproval> getPendingApprovals();
}
