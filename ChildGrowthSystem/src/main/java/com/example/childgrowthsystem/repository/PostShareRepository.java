package com.example.childgrowthsystem.repository;

import com.childgrowth.tracking.model.PostShare;
import com.childgrowth.tracking.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostShareRepository extends JpaRepository<PostShare, Long> {
    List<PostShare> findByPost(BlogPost post);
    long countByPost(BlogPost post);
} 