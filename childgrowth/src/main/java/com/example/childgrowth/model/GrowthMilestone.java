package com.example.childgrowth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "GrowthMilestones")
@Data
public class GrowthMilestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long milestoneId;

    @ManyToOne
    @JoinColumn(name = "ChildID", nullable = false)
    private ChildProfile child;

    @Column(nullable = false)
    private String milestoneName;

    @Column(nullable = false)
    private LocalDate milestoneDate;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "RecordedByUserID", nullable = false)
    private User recordedBy;
}