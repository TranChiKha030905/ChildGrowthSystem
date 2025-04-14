package com.example.childgrowthsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    private String title;
    private String content;
    private String summary;
    private LocalDateTime publishedAt;
    private LocalDateTime lastModifiedAt;
    
    // Post metadata
    private String status; // DRAFT, PUBLISHED, ARCHIVED
    private String category;
    
    @ElementCollection
    @CollectionTable(name = "blog_post_tags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "tag")
    private List<String> tags;
    
    private String featuredImageUrl;
    
    // SEO information
    private String metaTitle;
    private String metaDescription;
    private String slug;
    
    // Engagement metrics
    @Column(name = "view_count")
    private Integer viewCount = 0;
    
    @Column(name = "like_count")
    private Integer likeCount = 0;
    
    @Column(name = "comment_count")
    private Integer commentCount = 0;
    
    @Column(name = "share_count")
    private Integer shareCount = 0;
    
    // Additional information
    private boolean isFeatured;
    private boolean isPinned;
    private String language;
    private String readingTime;
    
    // Moderation
    private boolean isApproved;
    private String approvedBy;
    private LocalDateTime approvedAt;
    private String rejectionReason;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (viewCount == null) viewCount = 0;
        if (likeCount == null) likeCount = 0;
        if (commentCount == null) commentCount = 0;
        if (shareCount == null) shareCount = 0;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
} 