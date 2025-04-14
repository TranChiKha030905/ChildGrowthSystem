package com.example.childgrowthsystem.repository;

import com.childgrowth.tracking.model.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {
    List<SubscriptionPlan> findByIsActive(boolean isActive);
    List<SubscriptionPlan> findByIsPopular(boolean isPopular);
    List<SubscriptionPlan> findByBillingCycle(String billingCycle);
    List<SubscriptionPlan> findByHasDoctorConsultation(boolean hasDoctorConsultation);
    List<SubscriptionPlan> findByDisplayOrderLessThanEqual(Integer maxOrder);
    List<SubscriptionPlan> findByPromotionalCode(String promotionalCode);
} 