package com.example.within_back.dto;

import com.example.within_back.entity.Comment;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
public class CommentsResDto implements Serializable {

    private Long id;
    private Long postId;
    private String authorName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public CommentsResDto(Comment comment) {
        this.id = comment.getId();
        this.postId = comment.getPost().getId();
        this.authorName = comment.getAuthor().getNickname();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
