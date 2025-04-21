package com.childgrowth.tracking.model;

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
    //giá cho những gói
    private int price;
    private int maxChildren;
    private boolean allowDoctorConsult;

    //getter setter

    public MembershipPlan(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxChildren(int maxChildren) {
        this.maxChildren = maxChildren;
    }

    public void setAllowDoctorConsult(boolean allowDoctorConsult) {
        this.allowDoctorConsult = allowDoctorConsult;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxChildren() {
        return maxChildren;
    }

    public boolean isAllowDoctorConsult() {
        return allowDoctorConsult;
    }
}
