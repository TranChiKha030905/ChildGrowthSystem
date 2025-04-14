package com.example.childgrowthsystem.repository;

import com.childgrowth.tracking.model.Comment;
import com.childgrowth.tracking.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(BlogPost post);
    List<Comment> findByPostOrderByCreatedAtDesc(BlogPost post);
} 