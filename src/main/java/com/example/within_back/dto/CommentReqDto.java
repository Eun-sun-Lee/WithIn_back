package com.example.within_back.dto;

import com.example.within_back.entity.Comment;
import com.example.within_back.entity.Post;
import com.example.within_back.entity.User;

public class CommentReqDto {
    private User author;
    private String content;

    public Comment toEntity(Post post) {

        return new Comment(post, author, content);
    }
}

