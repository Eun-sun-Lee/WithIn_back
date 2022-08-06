package com.example.within_back.service;

import com.example.within_back.dto.CommentsResDto;
import com.example.within_back.entity.Comment;
import com.example.within_back.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PostService {

    @Autowired
    CommentRepository commentRepository;
    public ArrayList<CommentsResDto> getComments(long postId) {
        ArrayList<Comment> data = commentRepository.findByPostIdOrderByCreatedAt(postId);

        ArrayList<CommentsResDto> result = new ArrayList<>();

        for(Comment comment : data) {
            CommentsResDto temp = new CommentsResDto(comment);
            result.add(temp);
        }

        return result;
    }
}
