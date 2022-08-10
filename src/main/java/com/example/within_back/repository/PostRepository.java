package com.example.within_back.repository;

import com.example.within_back.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public ArrayList<Post> findByAuthorId(Long userId);
}



