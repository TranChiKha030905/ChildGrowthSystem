package com.childgrowth.tracking.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Data
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private LocalDate lastCheckup;

    @Transient // Đánh dấu đây là trường không lưu trong DB
    public int getAge() {
        if (dateOfBirth == null) return 0;
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }


    @ManyToOne
    private User parent;

    @ManyToOne
    private User doctor;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<GrowthRecord> growthRecords;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<MedicalEvent> medicalEvents;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<SleepRecord> sleepRecords;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<NutritionRecord> nutritionRecords;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<ActivityRecord> activityRecords;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<VaccinationRecord> vaccinationRecords;

    private String notes;
    private String profilePicture;
    private boolean isActive = true;

    @OneToOne(mappedBy = "child", cascade = CascadeType.ALL)
    private ChildProfile childProfile; // Quan hệ One-to-One với ChildProfile
} 