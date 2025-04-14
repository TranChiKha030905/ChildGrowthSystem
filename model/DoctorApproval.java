package com.childgrowth.tracking.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "doctor_approvals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String specialization;
    private String licenseNumber;
    private String hospital;
    private String experience;
    private String reason;
    private LocalDateTime requestDate;
    private LocalDateTime approvalDate;
    private boolean approved;
    private String rejectionReason;
    private String status;
} 