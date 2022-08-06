package com.example.within_back.service;

import com.example.within_back.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;
    public boolean isEmailRepeat(String email) {
        return (userRepository.findByEmail(email) == null) ? false : true;
    }

    public boolean isNicknameRepeat(String nickname) {
        return (userRepository.findByNickname(nickname) == null) ? false : true;
    }
}
