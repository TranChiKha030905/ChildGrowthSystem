package com.example.childgrowthsystem.demo.services.impl;

import com.example.childgrowthsystem.demo.repositories.ContentPostRepository;
import com.example.childgrowthsystem.demo.repositories.UserRepository;
import com.example.childgrowthsystem.demo.services.ContentPostService;
import com.example.childgrowthsystem.entity.ContentPosts;
import com.example.childgrowthsystem.entity.Users;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContentPostServiceImpl implements ContentPostService
{

    private final ContentPostRepository contentPostRepository;
    private final UserRepository userRepository;

    public ContentPostServiceImpl(ContentPostRepository contentPostRepository, UserRepository userRepository)
    {
        this.contentPostRepository = contentPostRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ContentPosts createPost(ContentPosts post)
    {
        post.setCreatedAt(LocalDateTime.now()); // Đặt thời gian tạo
        return contentPostRepository.save(post);
    }

    @Override
    public List<ContentPosts> getAllPosts()
    {
        return contentPostRepository.findAll();
    }

    @Override
    public Optional<ContentPosts> getPostById(int postId)
    {
        return contentPostRepository.findById(postId);
    }

    @Override
    public List<ContentPosts> getPostsByType(ContentPosts.PostType postType)
    {
        return contentPostRepository.findByPostType(postType);
    }

    @Override
    public List<ContentPosts> getPostsByAuthorId(int authorId)
    {
        Optional<Users> author = userRepository.findById(authorId);
        return author.map(contentPostRepository::findByAuthor).orElse(List.of());
    }

    @Override
    public void deletePost(int postId)
    {
        contentPostRepository.deleteById(postId);
    }
}
