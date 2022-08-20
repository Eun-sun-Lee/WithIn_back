package com.example.within_back.dto;

import com.example.within_back.entity.Comment;
import com.example.within_back.entity.Post;
import com.example.within_back.entity.User;
import com.example.within_back.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public class CommentReqDto {
    private String content;
    public Comment toEntity(Post post, User author) {
        return new Comment(post, author, content);
    }
}