package com.childgrowth.tracking.service.impl;

import com.childgrowth.tracking.model.SubscriptionPlan;
import com.childgrowth.tracking.repository.SubscriptionPlanRepository;
import com.childgrowth.tracking.service.SubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    @Transactional
    public SubscriptionPlan createPlan(SubscriptionPlan plan) {
        validatePlanData(plan);
        plan.setActive(true);
        return subscriptionPlanRepository.save(plan);
    }

    @Override
    public SubscriptionPlan getPlanById(Long id) {
        return subscriptionPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription plan not found with id: " + id));
    }

    @Override
    public List<SubscriptionPlan> getAllActivePlans() {
        return subscriptionPlanRepository.findByIsActive(true);
    }

    @Override
    public List<SubscriptionPlan> getPopularPlans() {
        return subscriptionPlanRepository.findByIsPopular(true);
    }

    @Override
    public List<SubscriptionPlan> getPlansByBillingCycle(String billingCycle) {
        return subscriptionPlanRepository.findByBillingCycle(billingCycle);
    }

    @Override
    public List<SubscriptionPlan> getPlansWithDoctorConsultation() {
        return subscriptionPlanRepository.findByHasDoctorConsultation(true);
    }

    @Override
    @Transactional
    public void updatePlan(SubscriptionPlan plan) {
        validatePlanData(plan);
        if (!subscriptionPlanRepository.existsById(plan.getId())) {
            throw new RuntimeException("Subscription plan not found with id: " + plan.getId());
        }
        subscriptionPlanRepository.save(plan);
    }

    @Override
    @Transactional
    public void deactivatePlan(Long id) {
        SubscriptionPlan plan = getPlanById(id);
        plan.setActive(false);
        subscriptionPlanRepository.save(plan);
    }

    @Override
    @Transactional
    public void activatePlan(Long id) {
        SubscriptionPlan plan = getPlanById(id);
        plan.setActive(true);
        subscriptionPlanRepository.save(plan);
    }

    @Override
    @Transactional
    public void applyPromotion(Long id, String promotionalCode, Double promotionalPrice) {
        SubscriptionPlan plan = getPlanById(id);
        plan.setPromotionalCode(promotionalCode);
        plan.setPromotionalPrice(promotionalPrice);
        plan.setPromotionStartDate(LocalDateTime.now());
        plan.setPromotionEndDate(LocalDateTime.now().plusMonths(1)); // Default 1 month promotion
        subscriptionPlanRepository.save(plan);
    }

    @Override
    @Transactional
    public void removePromotion(Long id) {
        SubscriptionPlan plan = getPlanById(id);
        plan.setPromotionalCode(null);
        plan.setPromotionalPrice(null);
        plan.setPromotionStartDate(null);
        plan.setPromotionEndDate(null);
        subscriptionPlanRepository.save(plan);
    }

    @Override
    public boolean isPlanActive(Long id) {
        SubscriptionPlan plan = getPlanById(id);
        return plan.isActive();
    }

    private void validatePlanData(SubscriptionPlan plan) {
        if (plan.getName() == null || plan.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Plan name cannot be empty");
        }
        if (plan.getPrice() == null || plan.getPrice() <= 0) {
            throw new IllegalArgumentException("Plan price must be greater than 0");
        }
        if (plan.getCurrency() == null || plan.getCurrency().trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be empty");
        }
        if (plan.getBillingCycle() == null || plan.getBillingCycle().trim().isEmpty()) {
            throw new IllegalArgumentException("Billing cycle cannot be empty");
        }
        if (plan.getMaxChildren() == null || plan.getMaxChildren() <= 0) {
            throw new IllegalArgumentException("Maximum number of children must be greater than 0");
        }
    }
} 