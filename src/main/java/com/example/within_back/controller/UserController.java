package com.example.within_back.controller;

import com.example.within_back.dto.*;
import com.example.within_back.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{userId}/posts")
    private ArrayList<PostResDto> getMyPosts(@PathVariable Long userId){
        return userService.getMyPosts(userId);
    }

    @GetMapping("/{userId}/messages")
    private ArrayList<MessageResDto> getMyMessages(@PathVariable Long userId){
        return userService.getMyMessages(userId);
    }

    @PostMapping("/{userId}/messages/{partnerId}")
    private Long sendMessage(@PathVariable Long userId, @PathVariable Long partnerId, @RequestBody MessageReqDto messageReqDto){
        return userService.sendMessage(userId, partnerId, messageReqDto);
    }

    @GetMapping("/{userId}/messages/{partnerId}")
    private ArrayList<MessageResDto> getMessagesWithPartner(@PathVariable Long userId, @PathVariable Long partnerId) {
        return userService.getMessagesWithPartner(userId, partnerId);
    }

    @GetMapping("/{userId}/myGroup")
    public ArrayList<HobbyResDto> getHobby(@PathVariable Long userId) {
        return userService.getHobby(userId);
    }

    @PutMapping("/{userId}/myGroup")
    public Boolean updateHobby(@PathVariable Long userId, @RequestBody HobbyReqDto hobbyReqDto) {
        return userService.updateHobby(userId, hobbyReqDto);
    }

    @GetMapping("/user/emailList")
    public boolean isEmailRepeat(@RequestParam("email") String email) {

        return userService.isEmailRepeat(email);
    }

    @GetMapping("/user/nicknameList")
    public boolean isNicknameRepeat(@RequestParam("nickname") String nickname) {
        return userService.isNicknameRepeat(nickname);
    }

    @GetMapping("/user/{uid}")
    public Long getUserId(@PathVariable String uid){
        return userService.getUserId(uid);
    }

    @PostMapping("/create")
    public Long createUser(@RequestBody UserReqDto userReqDto){
        return userService.createUser(userReqDto);
    }
}
