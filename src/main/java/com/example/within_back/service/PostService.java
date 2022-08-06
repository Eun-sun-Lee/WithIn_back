package com.example.within_back.service;

import com.example.within_back.dto.PostReqDto;
import com.example.within_back.dto.PostResDto;
import com.example.within_back.entity.Board;
import com.example.within_back.entity.Post;
import com.example.within_back.repository.BoardRepository;
import com.example.within_back.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PostService {

    @Autowired
    static
    PostRepository postRepository;
    BoardRepository boardRepository;

    public static ArrayList<PostResDto> getPosts(String category){
        ArrayList<Post> data = postRepository.findByCategory(category);
        ArrayList<PostResDto> result = new ArrayList<>();
        for(Post post : data) {
            PostResDto temp = new PostResDto(post);
            result.add(temp)
;        }
        return result;
    }
    //게시판 게시물 조회 (GET 방식)


    public Long save(PostReqDto postReqDto, String category){
        Board board = boardRepository.findByCategory(category);
        return postRepository.save(postReqDto.toEntity(board)).getId();
    } //게시물 작성 (POST 방식)


    public PostResDto findById(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new PostResDto(post);
    } //게시글 조회
}
