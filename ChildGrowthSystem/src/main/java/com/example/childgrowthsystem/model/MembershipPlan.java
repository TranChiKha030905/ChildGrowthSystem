package com.example.childgrowthsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "membership_plan")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int maxChildren;
    private boolean allowDoctorConsult;
}
