package com.childgrowth.tracking.repository;

import com.childgrowth.tracking.model.MembershipPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MembershipPlanRepository extends JpaRepository<MembershipPlan, Long> {
    Optional<MembershipPlan> findById(Long aLong);
    @Query("SELECT new map(m.id as id, m.name as name) FROM MembershipPlan m")
    List<Map<String, Object>> findAllPlansSimplified();
}
