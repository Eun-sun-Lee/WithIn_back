package com.example.within_back.service;

import com.example.within_back.entity.Post;
import com.example.within_back.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public int addLikes(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 post id입니다."));
        post.setLiked(post.getLiked()+1);
        postRepository.save(post);
        return post.getLiked();
    }
}
