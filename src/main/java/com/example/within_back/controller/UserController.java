package com.example.within_back.controller;

import com.example.within_back.dto.HobbyReqDto;
import com.example.within_back.dto.HobbyResDto;
import com.example.within_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{userId}/myGroup")
    public ArrayList<HobbyResDto> getHobby(@PathVariable Long userId) {
        return userService.getHobby(userId);
    }

    @PutMapping("/{userId}/myGroup")
    public void updateHobby(@PathVariable Long userId, @RequestBody HobbyReqDto hobbyReqDto) {
        userService.updateHobby(userId, hobbyReqDto);
    }

    @GetMapping("/user/emailList")
    public boolean isEmailRepeat(@RequestParam("email") String email) {

        return userService.isEmailRepeat(email);
    }

    @GetMapping("/user/nicknameList")
    public boolean isNicknameRepeat(@RequestParam("nickname") String nickname) {
        return userService.isNicknameRepeat(nickname);
    }
}
