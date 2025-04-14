package com.example.childgrowthsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private SubscriptionPlan plan;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status; // ACTIVE, EXPIRED, CANCELLED, PENDING
    
    // Payment information
    private String paymentMethod;
    private String transactionId;
    private Double amount;
    private String currency;
    private LocalDateTime paymentDate;
    
    // Auto-renewal settings
    private boolean autoRenew;
    private String renewalFrequency; // MONTHLY, YEARLY
    
    // Additional information
    private String notes;
    private String cancelledBy;
    private LocalDateTime cancellationDate;
    private String cancellationReason;
} 