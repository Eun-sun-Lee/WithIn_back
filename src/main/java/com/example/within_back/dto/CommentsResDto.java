package com.example.within_back.dto;

import com.example.within_back.entity.Comment;
import com.example.within_back.entity.Post;
import com.example.within_back.entity.User;

import java.time.LocalDateTime;

public class CommentsResDto {

    private Long id;
    private Post post;
    private User author;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public CommentsResDto(Comment comment) {
        this.id = comment.getId();
        this.post = comment.getPost();
        this.author = comment.getAuthor();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getModifiedAt();
    }
}
