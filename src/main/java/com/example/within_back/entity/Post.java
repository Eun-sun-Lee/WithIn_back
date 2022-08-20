package com.example.within_back.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"commentList", "board", "author"})
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    private String title;

    private String content;

    private int liked;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    @JsonIgnoreProperties({"post"})
    @OneToMany
    private List<Likes> likesList;

    @Builder
    public Post(Board board, User author, String title, String content, int liked) {
        this.board = board;
        this.author = author;
        this.title = title;
        this.content = content;
        this.liked = liked;
    }
}
