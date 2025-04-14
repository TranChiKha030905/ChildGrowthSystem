package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.SubscriptionPlan;
import java.util.List;

public interface SubscriptionPlanService {
    SubscriptionPlan createPlan(SubscriptionPlan plan);
    SubscriptionPlan getPlanById(Long id);
    List<SubscriptionPlan> getAllActivePlans();
    List<SubscriptionPlan> getPopularPlans();
    List<SubscriptionPlan> getPlansByBillingCycle(String billingCycle);
    List<SubscriptionPlan> getPlansWithDoctorConsultation();
    void updatePlan(SubscriptionPlan plan);
    void deactivatePlan(Long id);
    void activatePlan(Long id);
    void applyPromotion(Long id, String promotionalCode, Double promotionalPrice);
    void removePromotion(Long id);
    boolean isPlanActive(Long id);
} 