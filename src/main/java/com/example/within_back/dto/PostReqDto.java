package com.example.within_back.dto;

import com.example.within_back.entity.Board;
import com.example.within_back.entity.Post;
import com.example.within_back.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostReqDto {
    private User author; //삭제 필요
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
