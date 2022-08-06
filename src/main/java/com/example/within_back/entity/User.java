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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String myPost;

    private String army;

    private String position;

    private String mbti;

//    @OneToMany(mappedBy = "user")
//    private List<Hobby> myHobbies = new ArrayList();

    @OneToMany(mappedBy = "user")
    private List<Message> myMessages = new ArrayList();

    @OneToMany(mappedBy = "author")
    private List<Post> myPosts = new ArrayList();

    @OneToMany(mappedBy = "author")
    private List<Comment> myComments = new ArrayList();

    @Builder
    public User(String nickname, String myPost, String army, String position, String mbti) {
        this.nickname = nickname;
        this.myPost = myPost;
        this.army = army;
        this.position = position;
        this.mbti = mbti;
    }
}
