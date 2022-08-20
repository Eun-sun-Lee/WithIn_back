package com.example.within_back.dto;

import com.example.within_back.entity.Hobby;
import com.example.within_back.entity.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class HobbyResDto implements Serializable {
    private String category;
    private String describe;

    public HobbyResDto(Hobby hobby){
        this.category = hobby.getCategory();
        this.describe = "hobby";
    }

    public HobbyResDto(String categoryName, String describe){
        this.category = categoryName;
        this.describe = describe;
    }
}
