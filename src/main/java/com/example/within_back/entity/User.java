package com.example.within_back.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"myMessages", "myPosts", "myComments"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid;
    private String email;
    private String nickname;
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
    public User(String email, String nickname, String army, String position, String mbti) {
        this.email = email;
        this.nickname = nickname;
        this.army = army;
        this.position = position;
        this.mbti = mbti;
    }
}
