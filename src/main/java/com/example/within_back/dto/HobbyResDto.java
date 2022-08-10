package com.example.within_back.dto;

import com.example.within_back.entity.Hobby;
import com.example.within_back.entity.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class HobbyResDto implements Serializable {
    private Long id;
    private User user;
    private String category;
    private String army;
    private String position;
    private String mbti;

    public HobbyResDto(Hobby hobby, User user){
        this.id = hobby.getId();
        this.user = hobby.getUser();
        this.category = hobby.getCategory();
        this.army = user.getArmy();
        this.position = user.getPosition();
        this.mbti = user.getMbti();
    }
}
