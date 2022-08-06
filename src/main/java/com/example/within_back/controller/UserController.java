package com.example.within_back.controller;

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

}
