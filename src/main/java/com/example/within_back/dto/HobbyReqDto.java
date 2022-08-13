package com.example.within_back.dto;

import com.example.within_back.entity.Hobby;
import com.example.within_back.entity.User;
import com.example.within_back.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
public class HobbyReqDto implements Serializable {
    private String army;
    private String position;
    private String mbti;
    private ArrayList<String> categories = new ArrayList<String>();

    public ArrayList<String> getCategories() {
        return categories;
    }

    public Hobby toHobbyEntity(User user, String category) {
        return new Hobby(user, category);
    }

    public User toUserEntity(String email, String nickname) {
        return new User(email, nickname, army, position, mbti);
    }
}