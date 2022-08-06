package com.example.within_back.dto;

import com.example.within_back.entity.Comment;
import com.example.within_back.entity.Post;
import com.example.within_back.entity.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CommentsResDto implements Serializable {

    private Long id;
    private Post post;
    private User author;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public CommentsResDto(Comment comment) {
        this.id = comment.getId();
        this.post = comment.getPost();
        this.author = comment.getAuthor();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}


//package com.example.within_back.dto;
//
//import com.example.within_back.entity.Comment;
//import com.example.within_back.entity.Post;
//import com.example.within_back.entity.User;
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import lombok.Getter;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//@Getter
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
//public class CommentsResDto implements Serializable {
//
//    private Long id;
//    private Long postId;
//    private String authorName;
//    private String content;
//    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;
//
//
//    public CommentsResDto(Comment comment) {
//        this.id = comment.getId();
//        this.postId = comment.getPost().getId();
//        this.authorName = comment.getAuthor().getNickname();
//        this.content = comment.getContent();
//        this.createdAt = comment.getCreatedAt();
//        this.modifiedAt = comment.getModifiedAt();
//    }
//}
