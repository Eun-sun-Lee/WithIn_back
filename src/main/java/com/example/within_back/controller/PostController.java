package com.example.within_back.controller;

import com.example.within_back.dto.BoardResDto;
import com.example.within_back.dto.PostReqDto;
import com.example.within_back.dto.PostResDto;
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
    
    @Autowired
    UserService userService;

    @RequestMapping(value = "/{userId}/boards", method = RequestMethod.GET)
    public ArrayList<BoardResDto> getMyBoard(@PathVariable Long userId) throws Exception {
        return userService.getMyBoard(userId);

    } //내 게시판 조회

    @RequestMapping(value = "/boards/{category}", method = RequestMethod.GET)
    public ArrayList<PostResDto> getPosts(@PathVariable String category) {
        return postService.getPosts(category);
    }
    //게시판 게시물 조회 (GET 방식)


//    @RequestMapping(value="/boards/{category}", method=RequestMethod.POST)
//    public Long postWrite(@RequestBody PostResDto postResDto, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
//        postService.postWrite(postResDto,multipartHttpServletRequest);
//        return
//    } //게시물 작성 (POST 방식)


    @RequestMapping(value="/boards/{category}", method=RequestMethod.POST)
    public Long save(@RequestBody PostReqDto postResDto, @PathVariable String category, @RequestParam("userId") Long userId){
        return postService.save(postResDto,category,userId);
    } //게시물 작성 (POST 방식)

    @RequestMapping(value = "/boards/posts/{postId}", method = RequestMethod.GET)
    public PostResDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    } //게시글 조회

    @GetMapping("/boards/{postId}/comments")
    public ArrayList<CommentsResDto> getComments(@PathVariable Long postId) {

        return postService.getComments(postId);
    }

    @PostMapping("/boards/{postId}/comments")
    public Long writeComment(@PathVariable Long postId, @RequestParam Long authorId, @RequestBody CommentReqDto commentReqDto) {

        return postService.writeComment(postId, authorId, commentReqDto);
    }
}
