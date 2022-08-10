package com.example.within_back.repository;

import com.example.within_back.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.util.ArrayList;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    ArrayList<Comment> findByPostIdOrderByCreatedAt(Long postId);
}
