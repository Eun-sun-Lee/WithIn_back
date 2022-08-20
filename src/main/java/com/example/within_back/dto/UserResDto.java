package com.example.within_back.dto;

import lombok.Getter;

@Getter
public class UserResDto {
    private Long userId;
    private String userNickname;

    public UserResDto(Long userId, String userNickname){
        this.userId = userId;
        this.userNickname = userNickname;
    }
}
