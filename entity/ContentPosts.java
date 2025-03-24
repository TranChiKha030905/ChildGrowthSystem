package com.example.childgrowthsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ContentPosts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentPosts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postID;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostType postType;

    // Một tác giả có thể có nhiều bài viết
    @ManyToOne
    @JoinColumn(name = "AuthorID", nullable = false)
    private Users author;

    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public enum PostType {
        Blog, FAQ
    }
}