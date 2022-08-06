package com.example.within_back.controller;

import com.example.within_back.dto.PostResDto;
import com.example.within_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
