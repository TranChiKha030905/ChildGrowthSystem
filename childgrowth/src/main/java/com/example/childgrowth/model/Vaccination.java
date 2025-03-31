package com.example.childgrowth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Vaccinations")
@Data
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vaccinationId;

    @ManyToOne
    @JoinColumn(name = "ChildID", nullable = false)
    private ChildProfile child;

    @Column(nullable = false)
    private String vaccineName;

    @Column(nullable = false)
    private LocalDate vaccinationDate;

    private LocalDate nextDueDate;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "RecordedByUserID", nullable = false)
    private User recordedBy;
}