package com.example.within_back.service;

import com.example.within_back.dto.BoardResDto;
import com.example.within_back.dto.PostResDto;
import com.example.within_back.entity.Board;
import com.example.within_back.entity.Post;
import com.example.within_back.repository.BoardRepository;
import com.example.within_back.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public ArrayList<PostResDto> getPosts(String category){
        ArrayList<Post> data = postRepository.findByCategory(category);
        ArrayList<PostResDto> result = new ArrayList<>();

        for(Post post : data) {
            PostResDto temp = new PostResDto(post);
            result.add(temp)
;        }
        return result;
    }
    //게시판 게시물 조회 (GET 방식)


    public Long save(PostResDto postResDto, String category){
        return postRepository.save(postResDto.toEntity(category)).getId();
    } //게시물 작성 (POST 방식)


    public PostResDto findById(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new PostResDto(post);
    } //게시글 조회
}
