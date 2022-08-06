package com.example.within_back.repository;

import com.example.within_back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByNickname(String nickname);
}
