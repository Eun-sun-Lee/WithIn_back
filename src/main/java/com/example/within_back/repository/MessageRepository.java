package com.example.within_back.repository;

import com.example.within_back.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    public ArrayList<Message> findByUserIdOrPartnerId(Long userId, Long partnerId);

    public ArrayList<Message> findByUserIdAndPartnerId(Long userId, Long partnerId);
}
