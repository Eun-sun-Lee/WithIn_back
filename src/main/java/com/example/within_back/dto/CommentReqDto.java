package com.example.within_back.dto;

import com.example.within_back.entity.Comment;
import com.example.within_back.entity.Post;
import com.example.within_back.entity.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class CommentReqDto {
    private User author;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Comment toEntity(Post post) {
        return new Comment(post, author, content, createdAt, updatedAt);
    }
}

