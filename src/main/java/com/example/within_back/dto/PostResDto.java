package com.example.within_back.dto;

import com.example.within_back.entity.Post;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class PostResDto {

    private Long id;
    private String boardName;
    private String authorNickname;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int likeCount;
    private int commentCount;

    public PostResDto(Post post, int commentCount) {
        this.id = post.getId();
        this.boardName = post.getBoard().getBoardName();
        this.authorNickname = post.getAuthor().getNickname();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getModifiedAt();
        this.likeCount = post.getLiked();
        this.commentCount = commentCount;
    }
}
