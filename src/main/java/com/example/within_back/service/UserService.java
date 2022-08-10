package com.example.within_back.service;

import com.example.within_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public boolean isEmailRepeat(String email) {
        return (userRepository.findByEmail(email) == null) ? false : true;

    }

    public boolean isNicknameRepeat(String nickname) {
        return (userRepository.findByNickname(nickname) == null) ? false : true;
    }
}
