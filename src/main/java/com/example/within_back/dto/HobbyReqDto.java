package com.example.within_back.dto;

import com.example.within_back.entity.Hobby;
import com.example.within_back.entity.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class HobbyReqDto implements Serializable {
    private Long id;
    private User user;
    private String category;
    private String army;
    private String position;
    private String mbti;

    public HobbyReqDto(Hobby hobby, User user){
        this.id = hobby.getId();
        this.user = hobby.getUser();
        this.category = hobby.getCategory();
        this.army = user.getArmy();
        this.position = user.getPosition();
        this.mbti = user.getMbti();
    }

    public Hobby toHobbyEntity(User user) {
        return new Hobby(user, category);
    }

    public User toUserEntity(String email, String nickname, String myPost) {
        return new User(email, nickname, myPost, army, position, mbti);
    }
}