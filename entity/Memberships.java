package com.example.childgrowthsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "Memberships")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memberships {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int membershipID;

    // Một gói thành viên  về một người
    @OneToOne
    @JoinColumn(name = "UserID", nullable = false)
    private Users user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MembershipType membershipType;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, length = 50)
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus transactionStatus;

    public enum MembershipType {
        Basic, Premium
    }

    public enum TransactionStatus {
        Success, Failed
    }
}