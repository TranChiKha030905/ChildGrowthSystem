package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.BlogPost;
import com.childgrowth.tracking.model.User;
import java.time.LocalDateTime;
import java.util.List;

public interface BlogPostService {
    BlogPost createPost(BlogPost post);
    BlogPost getPostById(Long id);
    List<BlogPost> getAllPosts();
    List<BlogPost> getPublishedPosts();
    List<BlogPost> getPostsByAuthor(User author);
    List<BlogPost> getPostsByCategory(String category);
    List<BlogPost> getPostsByTags(List<String> tags);
    List<BlogPost> getPostsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<BlogPost> searchPosts(String keyword);
    void updatePost(BlogPost post);
    void publishPost(Long id);
    void unpublishPost(Long id);
    void deletePost(Long id);
    void addComment(Long postId, String comment, User user);
    void removeComment(Long postId, Long commentId);
    void likePost(Long postId, User user);
    void unlikePost(Long postId, User user);
    void sharePost(Long postId, User user, String platform);
} 