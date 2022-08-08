package com.example.within_back.dto;

import com.example.within_back.entity.Message;
import com.example.within_back.entity.User;
import lombok.Getter;

@Getter
public class MessageReqDto {

    private String content;

    public Message toEntity(User user, User partner){
        return new Message(user, partner, content);
    }
}
