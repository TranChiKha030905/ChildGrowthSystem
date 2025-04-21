package com.childgrowth.tracking.repository;

import com.childgrowth.tracking.model.Subscription;
import com.childgrowth.tracking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUser(User user);
    
    Optional<Subscription> findFirstByUserOrderByEndDateDesc(User user);
    
    List<Subscription> findByEndDateBefore(LocalDateTime date);
    
    @Query("SELECT s FROM Subscription s WHERE s.user = :user AND s.endDate < :date AND s.status = 'ACTIVE'")
    List<Subscription> findExpiredSubscriptions(@Param("user") User user, @Param("date") LocalDateTime date);
    
    @Query("SELECT s FROM Subscription s WHERE s.user = :user AND s.endDate BETWEEN :startDate AND :endDate")
    List<Subscription> findSubscriptionsInDateRange(
        @Param("user") User user, 
        @Param("startDate") LocalDateTime startDate, 
        @Param("endDate") LocalDateTime endDate
    );
    
    List<Subscription> findByUserAndStatus(User user, String status);
    
    List<Subscription> findByUserAndAutoRenew(User user, boolean autoRenew);

    Optional<Subscription> findTopByUserOrderByStartDateDesc(User user);

    List<Subscription> findByStatusAndEndDateBetween(String status, LocalDateTime startDate, LocalDateTime endDate);
} 