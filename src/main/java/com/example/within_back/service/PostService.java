package com.example.within_back.service;

import com.example.within_back.dto.CommentReqDto;
import com.example.within_back.dto.CommentsResDto;
import com.example.within_back.entity.Comment;
import com.example.within_back.entity.Post;
import com.example.within_back.entity.User;
import com.example.within_back.repository.CommentRepository;
import com.example.within_back.repository.PostRepository;
import com.example.within_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class PostService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public ArrayList<CommentsResDto> getComments(Long postId) {
        ArrayList<Comment> data = commentRepository.findByPostIdOrderByCreatedAt(postId);

        ArrayList<CommentsResDto> result = new ArrayList<>();

        for(Comment comment : data) {
            CommentsResDto temp = new CommentsResDto(comment);
            result.add(temp);
        }

        return result;
    }

    @Transactional
    public Long writeComment(Long postId, Long authorId, CommentReqDto commentReqDto) {
        User Author = userRepository.findById(authorId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 user id 입니다."));
        Post post = postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 post id 입니다."));
        return commentRepository.save(commentReqDto.toEntity(post, Author)).getId();
    }
}
