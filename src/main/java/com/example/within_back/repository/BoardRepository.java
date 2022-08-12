package com.example.within_back.repository;

import com.example.within_back.entity.Board;
import com.example.within_back.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    public Board findByCategory(String category);


}
