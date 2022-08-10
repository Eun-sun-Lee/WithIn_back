package com.example.within_back.service;

import com.example.within_back.dto.HobbyReqDto;
import com.example.within_back.dto.HobbyResDto;
import com.example.within_back.entity.Hobby;
import com.example.within_back.entity.User;
import com.example.within_back.repository.HobbyRepository;
import com.example.within_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class UserService {
    @Autowired
    HobbyRepository hobbyRepository;
    @Autowired
    UserRepository userRepository;

    public ArrayList<HobbyResDto> getHobby(Long userId) {
        ArrayList<Hobby> data = hobbyRepository.findByUserId(userId);
        ArrayList<HobbyResDto> result = new ArrayList<>();
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 USER ID입니다."));
        for (Hobby hobby : data) {
            HobbyResDto temp = new HobbyResDto(hobby, user);
            result.add(temp);
        }
        return result;
    }

    public void updateHobby(Long userId, HobbyReqDto hobbyReqDto) {
        ArrayList<String> categories = hobbyReqDto.getCategories();
        for (String category : categories) {
            hobbyRepository.save(hobbyReqDto.toHobbyEntity(userId, category));
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 USER ID입니다."));
        String category = user.getArmy();
        hobbyRepository.save(hobbyReqDto.toHobbyEntity(userId, category));
        category = user.getPosition();
        hobbyRepository.save(hobbyReqDto.toHobbyEntity(userId, category));
        category = user.getMbti();
        hobbyRepository.save(hobbyReqDto.toHobbyEntity(userId, category));

        String email = user.getEmail();
        String nickname = user.getNickname();
        userRepository.save(hobbyReqDto.toUserEntity(email, nickname));
    }
}