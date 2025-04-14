package com.example.childgrowthsystem.repository;

import com.childgrowth.tracking.model.DoctorApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoctorApprovalRepository extends JpaRepository<DoctorApproval, Long> {
    List<DoctorApproval> findByApproved(boolean approved);
    List<DoctorApproval> findByUserId(Long userId);
} 