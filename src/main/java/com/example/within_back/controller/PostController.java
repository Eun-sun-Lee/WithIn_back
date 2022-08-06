package com.example.within_back.controller;

import com.example.within_back.dto.CommentsResDto;
import com.example.within_back.service.PostService;
import com.example.within_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/boards/{postId}/comments")
    public ArrayList<CommentsResDto> getComments(@PathVariable long postId) {
        return postService.getComments(postId);
    }

}
