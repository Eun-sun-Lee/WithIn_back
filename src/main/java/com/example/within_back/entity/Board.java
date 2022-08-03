package com.example.within_back.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardName;

    private String category;

    @OneToMany(mappedBy = "board")
    private List<Post> posts = new ArrayList();

    @Builder
    public Board(String boardName, String category){
        this.boardName = boardName;
        this.category = category;
    }
}
