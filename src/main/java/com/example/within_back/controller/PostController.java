package com.example.within_back.controller;

import com.example.within_back.dto.CommentReqDto;
import com.example.within_back.dto.CommentsResDto;
import com.example.within_back.service.PostService;
import com.example.within_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/boards/{postId}/comments")
    public ArrayList<CommentsResDto> getComments(@PathVariable Long postId) {

        return postService.getComments(postId);
    }

    @PostMapping("/boards/{postId}/comments")
    public Long writeComment(@PathVariable Long postId, @RequestBody CommentReqDto commentReqDto) {

        return postService.writeComment(postId, commentReqDto);
    }
}