package com.example.within_back.controller;

import com.example.within_back.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PutMapping("/boards/{postId}/likes")
    private int addLikes(@PathVariable Long postId){
        return postService.addLikes(postId);
    }
}
