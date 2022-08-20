package com.example.within_back.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    @OneToMany(mappedBy = "board")
    private List<Post> posts = new ArrayList();

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Builder
    public Board(String boardName, String category){
        this.boardName = boardName;
        this.category = category;
    }
}
