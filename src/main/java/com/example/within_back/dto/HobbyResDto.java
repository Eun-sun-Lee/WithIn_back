package com.example.within_back.dto;

import com.example.within_back.entity.Hobby;
import com.example.within_back.entity.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class HobbyResDto implements Serializable {
    private String category;

    public HobbyResDto(Hobby hobby){
        this.category = hobby.getCategory();
    }

    public HobbyResDto(String categoryName){
        this.category = categoryName;
    }
}
