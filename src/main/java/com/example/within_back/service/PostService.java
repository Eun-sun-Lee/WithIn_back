package com.example.within_back.service;

import com.example.within_back.dto.PostReqDto;
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
    PostRepository postRepository;
    BoardRepository boardRepository;

    public ArrayList<PostReqDto> getPosts(String category){
        ArrayList<Post> data = postRepository.findByCategory(category);
        ArrayList<PostReqDto> result = new ArrayList<>();

        for(Post post : data) {
            PostReqDto temp = new PostReqDto(post);
            result.add(temp)
;        }
        return result;
    }
    //게시판 게시물 조회 (GET 방식)


    public Long save(PostReqDto postResDto, String category){
        Board board = boardRepository.findByCategory(category).orElseThrow(()-> new IllegalArgumentException("해당 카테고리는 유효하지 않는 카테고리입니다."));
        return postRepository.save(postResDto.toEntity(board)).getId();
    } //게시물 작성 (POST 방식)


    public PostReqDto findById(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new PostReqDto(post);
    } //게시글 조회
}
