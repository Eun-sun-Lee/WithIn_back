package com.example.within_back.dto;

import com.example.within_back.entity.Board;
import com.example.within_back.entity.Group;
import com.example.within_back.entity.Post;
import com.example.within_back.entity.User;

import java.time.LocalDateTime;

public class PostResDto {
    private Long id;
    private Board board;
    private User author;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int like;

    public PostResDto(Post post) {
        this.id=post.getId();
        this.board = post.getBoard();
        this.author = post.getAuthor();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.like = post.getLike();


        public Post toEntity(String category){
            return new Post(id=this.id,board=this.board, author=this.author, title=this.title,content=this.content, createdAt=this.createdAt,updatedAt=this.updatedAt,like=this.like);
        }
    }
}
