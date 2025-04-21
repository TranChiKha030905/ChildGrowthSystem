package com.childgrowth.tracking.service.impl;

import com.childgrowth.tracking.dto.ApiResponse;
import com.childgrowth.tracking.exception.*;
import com.childgrowth.tracking.model.*;
import com.childgrowth.tracking.repository.*;
import com.childgrowth.tracking.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private PostShareRepository postShareRepository;

    @Override
    @Transactional
    public BlogPost createPost(BlogPost post) {
        validatePostData(post);
        post.setCreatedAt(LocalDateTime.now());
        post.setStatus("DRAFT");
        return blogPostRepository.save(post);
    }

    @Override
    public BlogPost getPostById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID bài viết không được để trống");
        }
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new BlogPostNotFoundException("Không tìm thấy bài viết với id: " + id));
    }

    @Override
    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    @Override
    public List<BlogPost> getPublishedPosts() {
        return blogPostRepository.findByStatus("PUBLISHED");
    }

    @Override
    public List<BlogPost> getPostsByAuthor(User author) {
        if (author == null) {
            throw new IllegalArgumentException("Tác giả không được để trống");
        }
        return blogPostRepository.findByAuthor(author);
    }

    @Override
    public List<BlogPost> getPostsByCategory(String category) {
        if (!StringUtils.hasText(category)) {
            throw new IllegalArgumentException("Danh mục không được để trống");
        }
        return blogPostRepository.findByCategory(category);
    }

    @Override
    public List<BlogPost> getPostsByTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            throw new IllegalArgumentException("Tags không được để trống");
        }
        return blogPostRepository.findByTagsIn(tags);
    }

    @Override
    public List<BlogPost> getPostsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Ngày bắt đầu và kết thúc không được để trống");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Ngày bắt đầu không thể sau ngày kết thúc");
        }
        return blogPostRepository.findByCreatedAtBetween(startDate, endDate);
    }

    @Override
    public List<BlogPost> searchPosts(String keyword) {
        if (!StringUtils.hasText(keyword)) {
            throw new IllegalArgumentException("Từ khóa tìm kiếm không được để trống");
        }
        return blogPostRepository.findByTitleContainingOrContentContaining(keyword, keyword);
    }

    @Override
    @Transactional
    public void updatePost(BlogPost post) {
        if (post.getId() == null) {
            throw new IllegalArgumentException("ID bài viết không được để trống");
        }
        if (!blogPostRepository.existsById(post.getId())) {
            throw new BlogPostNotFoundException("Không tìm thấy bài viết với id: " + post.getId());
        }
        validatePostData(post);
        blogPostRepository.save(post);
    }

    @Override
    @Transactional
    public void publishPost(Long id) {
        BlogPost post = getPostById(id);
        post.setStatus("PUBLISHED");
        post.setPublishedAt(LocalDateTime.now());
        blogPostRepository.save(post);
    }

    @Override
    @Transactional
    public void unpublishPost(Long id) {
        BlogPost post = getPostById(id);
        post.setStatus("DRAFT");
        blogPostRepository.save(post);
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        if (!blogPostRepository.existsById(id)) {
            throw new BlogPostNotFoundException("Không tìm thấy bài viết với id: " + id);
        }
        blogPostRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addComment(Long postId, String comment, User user) {
        BlogPost post = getPostById(postId);
        Comment newComment = new Comment();
        newComment.setPost(post);
        newComment.setContent(comment);
        newComment.setUser(user);
        newComment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(newComment);
        
        // Update comment count
        post.setCommentCount(post.getCommentCount() + 1);
        blogPostRepository.save(post);
    }

    @Override
    @Transactional
    public void removeComment(Long postId, Long commentId) {
        BlogPost post = getPostById(postId);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Không tìm thấy bình luận với id: " + commentId));
        
        if (!comment.getPost().getId().equals(postId)) {
            throw new IllegalArgumentException("Bình luận không thuộc bài viết này");
        }
        
        commentRepository.delete(comment);
        
        // Update comment count
        post.setCommentCount(post.getCommentCount() - 1);
        blogPostRepository.save(post);
    }

    @Override
    @Transactional
    public void likePost(Long postId, User user) {
        BlogPost post = getPostById(postId);
        if (postLikeRepository.existsByPostAndUser(post, user)) {
            throw new IllegalStateException("Người dùng đã thích bài viết này");
        }
        
        PostLike like = new PostLike();
        like.setPost(post);
        like.setUser(user);
        like.setCreatedAt(LocalDateTime.now());
        postLikeRepository.save(like);
        
        // Update like count
        post.setLikeCount(post.getLikeCount() + 1);
        blogPostRepository.save(post);
    }

    @Override
    @Transactional
    public void unlikePost(Long postId, User user) {
        BlogPost post = getPostById(postId);
        PostLike like = postLikeRepository.findByPostAndUser(post, user)
                .orElseThrow(() -> new IllegalStateException("Người dùng chưa thích bài viết này"));
        
        postLikeRepository.delete(like);
        
        // Update like count
        post.setLikeCount(post.getLikeCount() - 1);
        blogPostRepository.save(post);
    }

    @Override
    @Transactional
    public void sharePost(Long postId, User user, String platform) {
        BlogPost post = getPostById(postId);
        
        PostShare share = new PostShare();
        share.setPost(post);
        share.setUser(user);
        share.setPlatform(platform);
        share.setSharedAt(LocalDateTime.now());
        postShareRepository.save(share);
        
        // Update share count
        post.setShareCount(post.getShareCount() + 1);
        blogPostRepository.save(post);
    }

    private void validatePostData(BlogPost post) {
        if (post == null) {
            throw new IllegalArgumentException("Bài viết không được để trống");
        }
        if (!StringUtils.hasText(post.getTitle())) {
            throw new IllegalArgumentException("Tiêu đề không được để trống");
        }
        if (!StringUtils.hasText(post.getContent())) {
            throw new IllegalArgumentException("Nội dung không được để trống");
        }
        if (post.getAuthor() == null) {
            throw new IllegalArgumentException("Tác giả không được để trống");
        }
    }
} 