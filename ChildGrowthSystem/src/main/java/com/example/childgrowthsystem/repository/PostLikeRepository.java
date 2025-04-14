package com.example.childgrowthsystem.repository;

import com.childgrowth.tracking.model.PostLike;
import com.childgrowth.tracking.model.BlogPost;
import com.childgrowth.tracking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByPostAndUser(BlogPost post, User user);
    long countByPost(BlogPost post);
    boolean existsByPostAndUser(BlogPost post, User user);
} 