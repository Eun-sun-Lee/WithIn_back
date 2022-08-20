package com.example.within_back.repository;

import com.example.within_back.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
    int countByPostId(Long postId);

    @Modifying
    @Query(value = "INSERT INTO likes(post_id, user_id) VALUES(:postId, :userId)", nativeQuery = true)
    void likes(long postId, long userId);

    @Modifying
    @Query(value = "DELETE FROM likes WHERE post_id = :postId AND user_id = :userId", nativeQuery = true)
    void unlikes(long postId, long userId);

    Likes findByPostIdAndUserId(Long postId, Long userId);
}
