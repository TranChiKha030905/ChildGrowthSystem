package com.example.childgrowthsystem.demo.repositories;

import com.example.childgrowthsystem.entity.ContentPosts;
import com.example.childgrowthsystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentPostRepository extends JpaRepository<ContentPosts, Integer>
{
    // Tìm các bài viết theo loại
    List<ContentPosts> findByPostType(ContentPosts.PostType postType);

    // Tìm các bài viết theo tác giả
    List<ContentPosts> findByAuthor(Users author);
}
