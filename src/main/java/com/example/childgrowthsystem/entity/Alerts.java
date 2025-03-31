package com.example.childgrowthsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Alerts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alerts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int alertID; // Khóa chính, tự động tăng, ánh xạ cột AlertID

    // Mối quan hệ @ManyToOne với ChildProfiles
    // Một Alerts thuộc về một ChildProfiles (một cảnh báo thuộc về một hồ sơ trẻ)
    @ManyToOne
    @JoinColumn(name = "ChildID", nullable = false)
    private ChildProfiles child;

    @Column(nullable = false, length = 50)
    private String alertType; // Loại cảnh báo (Fever, Allergy, ...), tối đa 50 ký tự

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description; // Mô tả chi tiết, kiểu TEXT

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime alertDate; // Ngày tạo cảnh báo, mặc định là thời gian hiện tại
}