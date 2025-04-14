package com.example.childgrowthsystem.repository;

import com.childgrowth.tracking.model.AdviceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdviceRequestRepository extends JpaRepository<AdviceRequest, Long> {
    List<AdviceRequest> findByResolved(boolean resolved);
}
