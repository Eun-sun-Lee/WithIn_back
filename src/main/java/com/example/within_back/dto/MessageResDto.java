package com.example.within_back.dto;

import com.example.within_back.entity.Message;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResDto {

    private Long id;
    private String userNickname;
    private String partnerNickname;
    private String content;
    private LocalDateTime createdAt;

    public MessageResDto(Message message) {
        this.id = message.getId();
        this.userNickname = message.getUser().getNickname();
        this.partnerNickname = message.getPartner().getNickname();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
    }
}
