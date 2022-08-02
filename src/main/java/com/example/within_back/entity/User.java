package com.example.within_back.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @OneToMany(mappedBy = "user")
    private List<Group> myGroups = new ArrayList();

    @OneToMany(mappedBy = "user")
    private List<Message> myMessages = new ArrayList();

    @OneToMany(mappedBy = "author")
    private List<Post> myPosts = new ArrayList();

    @OneToMany(mappedBy = "author")
    private List<Comment> myComments = new ArrayList();

    @Builder
    public User(String nickname, String myPost) {
        this.nickname = nickname;
        this.myPost = myPost;
    }
}
