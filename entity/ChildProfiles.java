package com.example.childgrowthsystem.entity;

import jakarta.persistence.*;
import lombok.*;
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

    @Column
    private Double heightCm;

    @Column
    private Double weightKg;

    @Column
    private Double bmi;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Alerts> alerts;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultations> consultations;

    public enum Gender {
        Male, Female
    }
}