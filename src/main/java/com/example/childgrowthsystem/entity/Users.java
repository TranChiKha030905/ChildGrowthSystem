package com.example.childgrowthsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleName roleName;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // Một Users có thể có n con
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ChildProfiles> childProfiles;

    // Một  người dùng chỉ có một gói thành viên
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Memberships membership;

    // Một người có nhiều yêu cầu tư vấn
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultations> consultationsAsUser;

    // Một người  có thể có nhiều buổi tư vấn
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultations> consultationsAsDoctor;

    // Một người có nhiều ý kiến
    @OneToMany(mappedBy = "generatedBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reports> reports;

    // Một người có nhiều bài viết
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContentPosts> contentPosts;

    public enum RoleName {
        Admin, Doctor, User
    }
}