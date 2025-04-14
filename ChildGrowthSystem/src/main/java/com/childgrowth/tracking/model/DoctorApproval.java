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

    //getter setter
    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public void setApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getHospital() {
        return hospital;
    }

    public String getExperience() {
        return experience;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    public boolean isApproved() {
        return approved;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public String getStatus() {
        return status;
    }

    public void setRequestedAt(LocalDateTime now) {

    }
}