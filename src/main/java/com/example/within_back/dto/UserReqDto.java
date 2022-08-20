package com.example.within_back.dto;

import com.example.within_back.entity.Unit;
import com.example.within_back.entity.User;
import lombok.Getter;

@Getter
public class UserReqDto {
    private String uid;
    private String email;
    private String nickname;
    private String army;
    private String position;
    private String mbti;

    public User toEntity(Unit unit){
        return User.builder().uid(uid).email(email).nickname(nickname).army(army).position(position).mbti(mbti).unit(unit).build();
    }
}
