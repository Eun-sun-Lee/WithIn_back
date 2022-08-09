package com.example.within_back.repository;

import com.example.within_back.entity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    public ArrayList<Hobby> findByUserId(Long userId);

}
