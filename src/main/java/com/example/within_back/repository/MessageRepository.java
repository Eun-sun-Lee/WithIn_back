package com.example.within_back.repository;

import com.example.within_back.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MessageRepository extends JpaRepository<Message, Long> {

    public ArrayList<Message> findByUserIdOrPartnerId(Long userId);
}
