package com.example.childgrowthsystem.demo.services;

import com.example.childgrowthsystem.entity.ContentPosts;
import java.util.List;
import java.util.Optional;

public interface ContentPostService
{
    // Tạo bài viết mới
    ContentPosts createPost(ContentPosts post);

    // Lấy tất cả bài viết
    List<ContentPosts> getAllPosts();

    // Lấy bài viết theo ID
    Optional<ContentPosts> getPostById(int postId);

    // Lấy bài viết theo loại
    List<ContentPosts> getPostsByType(ContentPosts.PostType postType);

    // Lấy bài viết theo tác giả
    List<ContentPosts> getPostsByAuthorId(int authorId);

    // Xóa bài viết
    void deletePost(int postId);
}
