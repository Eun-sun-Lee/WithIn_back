package com.example.within_back.controller;

import com.example.within_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/emailList")
    public boolean isEmailRepeat(@RequestParam("email") String email) {

        return userService.isEmailRepeat(email);
    }

    @GetMapping("/user/nicknameList")
    public boolean isNicknameRepeat(@RequestParam("nickname") String nickname) {
        return userService.isNicknameRepeat(nickname);
    }
}
