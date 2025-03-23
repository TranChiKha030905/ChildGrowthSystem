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
    private int alertID; // Khóa chính

    // Một đứa trẻ có thể có nhiều cảnh báo
    @ManyToOne
    @JoinColumn(name = "ChildID", nullable = false)
    private ChildProfiles child;

    @Column(nullable = false, length = 50)
    private String alertType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description; // Mô tả chi tiết

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime alertDate; // Ngày
}