package com.childgrowth.tracking.exception;

public class BlogPostNotFoundException extends RuntimeException {
    public BlogPostNotFoundException(String message) {
        super(message);
    }
} 