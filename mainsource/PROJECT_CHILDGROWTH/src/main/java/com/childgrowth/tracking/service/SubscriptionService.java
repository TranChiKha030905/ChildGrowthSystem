package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.Subscription;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.model.SubscriptionPlan;
import java.time.LocalDateTime;
import java.util.List;

public interface SubscriptionService {
    Subscription createSubscription(User user, SubscriptionPlan plan);
    Subscription getSubscriptionById(Long id);
    Subscription getActiveSubscriptionByUser(User user);
    List<Subscription> getSubscriptionsByUser(User user);
    List<Subscription> getSubscriptionsByStatus(String status);
    List<Subscription> getExpiringSubscriptions(LocalDateTime date);
    void updateSubscription(Subscription subscription);
    void cancelSubscription(Long id, String reason);
    void renewSubscription(Long id);
    void processPayment(Subscription subscription, String paymentMethod, String transactionId);
    boolean isSubscriptionActive(User user);
    void checkSubscriptionStatus();
} 