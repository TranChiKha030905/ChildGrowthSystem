package com.childgrowth.tracking.repository;

import com.childgrowth.tracking.model.BlogPost;
import com.childgrowth.tracking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findByAuthor(User author);
    List<BlogPost> findByStatus(String status);
    List<BlogPost> findByCategory(String category);
    List<BlogPost> findByIsApproved(boolean isApproved);
    List<BlogPost> findByIsFeatured(boolean isFeatured);
    List<BlogPost> findByPublishedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<BlogPost> findByTagsContaining(String tag);
    List<BlogPost> findByLanguage(String language);
    List<BlogPost> findByTagsIn(List<String> tags);
    List<BlogPost> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<BlogPost> findByTitleContainingOrContentContaining(String title, String content);
} 