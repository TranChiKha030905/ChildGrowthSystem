package com.childgrowth.tracking.service.impl;

import com.childgrowth.tracking.model.Subscription;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.model.SubscriptionPlan;
import com.childgrowth.tracking.repository.SubscriptionRepository;
import com.childgrowth.tracking.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    @Transactional
    public Subscription createSubscription(User user, SubscriptionPlan plan) {
        validateSubscriptionData(user, plan);
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(calculateEndDate(plan.getBillingCycle()));
        subscription.setStatus("PENDING");
        subscription.setAmount(plan.getPrice());
        subscription.setCurrency(plan.getCurrency());
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with id: " + id));
    }

    @Override
    public Subscription getActiveSubscriptionByUser(User user) {
        return subscriptionRepository.findTopByUserOrderByStartDateDesc(user)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
    }

    @Override
    public List<Subscription> getSubscriptionsByUser(User user) {
        return subscriptionRepository.findByUser(user);
    }

    @Override
    public List<Subscription> getSubscriptionsByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty");
        }
        // Since we don't have a direct findByStatus method, we'll use a workaround
        // This is not ideal for performance with large datasets
        List<Subscription> allSubscriptions = subscriptionRepository.findAll();
        return allSubscriptions.stream()
                .filter(sub -> status.equals(sub.getStatus()))
                .toList();
    }

    @Override
    public List<Subscription> getExpiringSubscriptions(LocalDateTime date) {
        return subscriptionRepository.findByEndDateBefore(date);
    }

    @Override
    @Transactional
    public void updateSubscription(Subscription subscription) {
        validateSubscriptionData(subscription.getUser(), subscription.getPlan());
        if (!subscriptionRepository.existsById(subscription.getId())) {
            throw new RuntimeException("Subscription not found with id: " + subscription.getId());
        }
        subscriptionRepository.save(subscription);
    }

    @Override
    @Transactional
    public void cancelSubscription(Long id, String reason) {
        Subscription subscription = getSubscriptionById(id);
        subscription.setStatus("CANCELLED");
        subscription.setCancelledBy(subscription.getUser().getUsername());
        subscription.setCancellationDate(LocalDateTime.now());
        subscription.setCancellationReason(reason);
        subscriptionRepository.save(subscription);
    }

    @Override
    @Transactional
    public void renewSubscription(Long id) {
        Subscription subscription = getSubscriptionById(id);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(calculateEndDate(subscription.getPlan().getBillingCycle()));
        subscription.setStatus("ACTIVE");
        subscriptionRepository.save(subscription);
    }

    @Override
    @Transactional
    public void processPayment(Subscription subscription, String paymentMethod, String transactionId) {
        subscription.setPaymentMethod(paymentMethod);
        subscription.setTransactionId(transactionId);
        subscription.setPaymentDate(LocalDateTime.now());
        subscription.setStatus("ACTIVE");
        subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isSubscriptionActive(User user) {
        Subscription subscription = getActiveSubscriptionByUser(user);
        return subscription != null && 
               "ACTIVE".equals(subscription.getStatus()) && 
               subscription.getEndDate().isAfter(LocalDateTime.now());
    }

    @Override
    @Transactional
    public void checkSubscriptionStatus() {
        List<Subscription> activeSubscriptions = subscriptionRepository.findByStatusAndEndDateBetween(
            "ACTIVE", LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1));
        LocalDateTime now = LocalDateTime.now();
        
        for (Subscription subscription : activeSubscriptions) {
            if (subscription.getEndDate().isBefore(now)) {
                subscription.setStatus("EXPIRED");
                subscriptionRepository.save(subscription);
            }
        }
    }

    private LocalDateTime calculateEndDate(String billingCycle) {
        LocalDateTime now = LocalDateTime.now();
        return switch (billingCycle.toUpperCase()) {
            case "MONTHLY" -> now.plusMonths(1);
            case "QUARTERLY" -> now.plusMonths(3);
            case "YEARLY" -> now.plusYears(1);
            default -> throw new IllegalArgumentException("Invalid billing cycle: " + billingCycle);
        };
    }

    private void validateSubscriptionData(User user, SubscriptionPlan plan) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (plan == null) {
            throw new IllegalArgumentException("Subscription plan cannot be null");
        }
        if (plan.getPrice() == null || plan.getPrice() <= 0) {
            throw new IllegalArgumentException("Invalid subscription plan price");
        }
        if (plan.getBillingCycle() == null || plan.getBillingCycle().trim().isEmpty()) {
            throw new IllegalArgumentException("Billing cycle cannot be empty");
        }
    }
} 