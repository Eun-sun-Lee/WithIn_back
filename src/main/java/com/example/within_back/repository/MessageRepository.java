package com.example.within_back.repository;

import com.example.within_back.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "select id, user_id, partner_id, content, created_at, null as modified_at\n" +
            "from (select *, rank() over(partition by partner_id order by created_at desc) AS ranking from (\n" +
            "select id, partner_id, :userId as user_id, content, created_at\n" +
            "from message\n" +
            "where user_id = :userId\n" +
            "union\n" +
            "select id, user_id as partner_id, :userId as user_id, content, created_at\n" +
            "from message\n" +
            "where partner_id = :userId) T) M\n" +
            "where ranking = :userId\n" +
            "order by created_at desc", nativeQuery = true)
    ArrayList<Message> findMyMessages(@Param("userId") Long userId);

    @Query(value = "select *\n" +
            "from message\n" +
            "where (user_id = :userId and partner_id = :partnerId) or (user_id = :partnerId and partner_id = :userId)\n" +
            "order by created_at", nativeQuery = true)
    ArrayList<Message> findByUserIdAndPartnerId(@Param("userId") Long userId, @Param("partnerId") Long partnerId);
}
