package com.example.childgrowthsystem.service;

import com.example.childgrowthsystem.demo.repositories.ContentPostRepository;
import com.example.childgrowthsystem.demo.repositories.UserRepository;
import com.example.childgrowthsystem.demo.services.impl.ContentPostServiceImpl;
import com.example.childgrowthsystem.entity.ContentPosts;
import com.example.childgrowthsystem.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContentPostServiceTest {

    @Mock
    private ContentPostRepository contentPostRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ContentPostServiceImpl contentPostService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePost() {
        ContentPosts post = new ContentPosts();
        post.setTitle("Sample");
        post.setContent("Nội dung");
        post.setPostType(ContentPosts.PostType.Blog);
        post.setAuthor(new Users());

        when(contentPostRepository.save(any(ContentPosts.class))).thenReturn(post);

        ContentPosts result = contentPostService.createPost(post);

        assertEquals("Sample", result.getTitle());
        verify(contentPostRepository, times(1)).save(post);
    }

    @Test
    public void testGetAllPosts() {
        when(contentPostRepository.findAll()).thenReturn(List.of(new ContentPosts(), new ContentPosts()));

        List<ContentPosts> result = contentPostService.getAllPosts();

        assertEquals(2, result.size());
        verify(contentPostRepository, times(1)).findAll();
    }

    @Test
    public void testGetPostById() {
        ContentPosts post = new ContentPosts();
        post.setPostID(1);
        when(contentPostRepository.findById(1)).thenReturn(Optional.of(post));

        Optional<ContentPosts> result = contentPostService.getPostById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getPostID());
    }

    @Test
    public void testGetPostsByType() {
        ContentPosts.PostType type = ContentPosts.PostType.FAQ;
        when(contentPostRepository.findByPostType(type)).thenReturn(List.of(new ContentPosts()));

        List<ContentPosts> result = contentPostService.getPostsByType(type);

        assertEquals(1, result.size());
        verify(contentPostRepository).findByPostType(type);
    }

    @Test
    public void testGetPostsByAuthorId() {
        Users author = new Users();
        author.setUserID(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(author));
        when(contentPostRepository.findByAuthor(author)).thenReturn(List.of(new ContentPosts()));

        List<ContentPosts> result = contentPostService.getPostsByAuthorId(1);

        assertEquals(1, result.size());
        verify(userRepository).findById(1);
        verify(contentPostRepository).findByAuthor(author);
    }

    @Test
    public void testDeletePost() {
        contentPostService.deletePost(5);
        verify(contentPostRepository, times(1)).deleteById(5);
    }
}
