package com.example.childgrowthsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ChildProfiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildProfiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int childID;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private Users user;

    @Column(nullable = false, length = 100)
    private String childName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(precision = 5, scale = 2)
    private BigDecimal heightCm;

    @Column(precision = 5, scale = 2)
    private BigDecimal weightKg;

    @Column(precision = 4, scale = 2)
    private BigDecimal bmi;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Alerts> alerts;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultations> consultations;

    public enum Gender {
        Male, Female
    }
}