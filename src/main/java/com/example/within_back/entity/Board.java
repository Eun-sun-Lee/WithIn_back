package com.example.within_back.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@ToString(exclude = "posts")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boardName;
    private String category;
    private String explanation = "";

    @OneToMany(mappedBy = "board")
    private List<Post> posts = new ArrayList();

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Builder
    public Board(String boardName, String category, String explanation, Unit unit){
        this.boardName = boardName;
        this.category = category;
        this.explanation = explanation;
        this.unit = unit;
    }
}
