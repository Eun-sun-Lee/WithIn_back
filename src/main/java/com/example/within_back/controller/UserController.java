package com.example.within_back.controller;

import com.example.within_back.dto.MessageReqDto;
import com.example.within_back.dto.MessageResDto;
import com.example.within_back.dto.PostResDto;
import com.example.within_back.service.UserService;
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
    private ArrayList<MessageResDto> getMessagesWithPartner(@PathVariable Long userId, @PathVariable Long partnerId){
        return userService.getMessagesWithPartner(userId, partnerId);
    }
}
