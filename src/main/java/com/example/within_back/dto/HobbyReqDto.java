package com.example.within_back.dto;

import com.example.within_back.entity.Hobby;
import com.example.within_back.entity.User;
import com.example.within_back.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class HobbyReqDto implements Serializable {
    @Autowired
    UserRepository userRepository;

    private Long userId;
    private String army;
    private String position;
    private String mbti;
    private ArrayList<String> categories = new ArrayList<String>();

    public ArrayList<String> getCategories() {
        return categories;
    }

    public Hobby toHobbyEntity(Long userId, String category) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 USER ID입니다."));
        return new Hobby(user, category);
    }

    public User toUserEntity(String email, String nickname) {
        return new User(email, nickname, army, position, mbti);
    }
}