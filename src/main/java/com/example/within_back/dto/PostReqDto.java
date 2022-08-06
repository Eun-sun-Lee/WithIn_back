package com.example.within_back.dto;

import com.example.within_back.entity.Board;
import com.example.within_back.entity.Post;
import com.example.within_back.entity.User;

import java.time.LocalDateTime;

public class PostReqDto {
    private User author;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int like;


        public Post toEntity(Board board){
            return new Post(board, author, title,content,like);
           // return new Post(board, author=this.author, title=this.title,content=this.content, createdAt=this.createdAt,updatedAt=this.updatedAt,like=this.like);
        }
    }
