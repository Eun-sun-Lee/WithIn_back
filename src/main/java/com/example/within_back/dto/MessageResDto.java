package com.example.within_back.dto;

import com.example.within_back.entity.Message;
import com.example.within_back.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResDto {

    private Long id;
    private String userNickname;
    private String partnerNickname;
    private Long partnerId;
    private String content;
    private LocalDateTime createdAt;

    public MessageResDto(Message message) {
        this.id = message.getId();
        this.userNickname = message.getUser().getNickname();
        this.partnerNickname = message.getPartner().getNickname();
        this.partnerId = message.getPartner().getId();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
    }

    public MessageResDto(Message message, User user) {
        this.id = message.getId();
        if(message.getUser() == user){
            this.userNickname = "";
        } else{
            this.userNickname = message.getUser().getNickname();
        }
        if(message.getPartner() == user){
            this.partnerNickname = "";
            this.partnerId = message.getUser().getId();
        } else{
            this.partnerNickname = message.getPartner().getNickname();
            this.partnerId = message.getPartner().getId();
        }
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
    }
}
