package com.example.within_back.service;

import com.example.within_back.dto.PostReqDto;
import com.example.within_back.dto.PostResDto;
import com.example.within_back.entity.Board;
import com.example.within_back.entity.Post;
import com.example.within_back.entity.User;
import com.example.within_back.repository.BoardRepository;
import com.example.within_back.repository.PostRepository;
import com.example.within_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;

    public ArrayList<PostResDto> getPosts(String category){
        Board board = boardRepository.findByCategory(category);
        List<Post> data= board.getPosts();
        ArrayList<PostResDto> result = new ArrayList<>();
        for(Post post : data) {
            PostResDto temp = new PostResDto(post);
            result.add(temp)
;        }
        return result;
    }
    //게시판 게시물 조회 (GET 방식)


    @Transactional
    public Long save(PostReqDto postReqDto, String category,Long userId){
        Board board = boardRepository.findByCategory(category);
        User author = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 user id 입니다."));
        return postRepository.save(postReqDto.toEntity(board,author)).getId();
    } //게시물 작성 (POST 방식)


    public PostResDto findById(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new PostResDto(post);
    } //게시글 조회
}
