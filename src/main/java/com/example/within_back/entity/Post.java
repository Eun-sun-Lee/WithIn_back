package com.example.within_back.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@ToString(exclude = {"commentList", "board_id", "user_id"})
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    private String title;

    private String content;

    private int liked;

    private int commentCount;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    @Builder
    public Post(Board board, User author, String title, String content, int liked) {
        this.board = board;
        this.author = author;
        this.title = title;
        this.content = content;
        this.liked = liked;
    }
}
