package com.example.childgrowthsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Consultations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int consultationID;

    // Một người có thể yêu cầu nhiều lần tư vấn
    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private Users user;

    //  mỗi lần tư vấn sẽ có 1 bác sĩ khác nhau tư vấn cũng có thể là bác sĩ đó
    @ManyToOne
    @JoinColumn(name = "DoctorID", nullable = false)
    private Users doctor;

    // một trẻ có nhiều báo cáo
    @ManyToOne
    @JoinColumn(name = "ChildID", nullable = false)
    private ChildProfiles child;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime requestDate; // Ngày yêu cầu tư vấn

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message; // Nội dung tư vấn, kiểu TEXT

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('Pending', 'Completed') DEFAULT 'Pending'")
    private Status status; // Trạng thái tư vấn

    @Column(columnDefinition = "TEXT")
    private String feedbackText; // Nội dung phản hồi

    @Column
    private LocalDateTime feedbackCreatedAt;// time

    public enum Status {
        Pending, Completed // Enum định nghĩa trạng thái tư vấn
    }
}