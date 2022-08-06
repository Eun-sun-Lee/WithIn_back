package com.example.within_back.repository;

import com.example.within_back.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PostRepository extends JpaRepository<Post, Long> {

    public ArrayList<Post> findByUserId(Long userId);
}
