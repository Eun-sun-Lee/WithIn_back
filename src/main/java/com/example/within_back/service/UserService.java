package com.example.within_back.service;

import com.example.within_back.dto.PostResDto;
import com.example.within_back.entity.Post;
import com.example.within_back.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    PostRepository postRepository;

    public ArrayList<PostResDto> getMyPosts(Long userId){
        ArrayList<Post> data = postRepository.findByUserId(userId);

        ArrayList<PostResDto> result = new ArrayList<>();

        for(Post post : data){
            result.add(new PostResDto(post));
        }

        return result;
    }
}
