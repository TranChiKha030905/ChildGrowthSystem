package com.example.childgrowthsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportID;

    @Column(nullable = false, length = 100)
    private String reportName; // Tên báo cáo

    @Column(columnDefinition = "TEXT")
    private String description; // Mô tả báo cáo

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime generatedAt; // Ngày tạo

    // Một người có thể có nhiều repot
    @ManyToOne
    @JoinColumn(name = "GeneratedByUserID", nullable = false)
    private Users generatedBy;

    @Column(columnDefinition = "JSON")
    private String reportData; // Dữ liệu báo cáo dạng JSON
}