package com.example.within_back.service;

import com.example.within_back.dto.MessageReqDto;
import com.example.within_back.dto.MessageResDto;
import com.example.within_back.dto.PostResDto;
import com.example.within_back.entity.Message;
import com.example.within_back.entity.Post;
import com.example.within_back.entity.User;
import com.example.within_back.repository.MessageRepository;
import com.example.within_back.repository.PostRepository;
import com.example.within_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    public ArrayList<PostResDto> getMyPosts(Long userId){
        ArrayList<Post> data = postRepository.findByAuthorId(userId);

        ArrayList<PostResDto> result = new ArrayList<>();

        for(Post post : data){
            result.add(new PostResDto(post));
        }

        return result;
    }

    public ArrayList<MessageResDto> getMyMessages(Long userId){
        ArrayList<Message> data = messageRepository.findByUserIdOrPartnerId(userId, userId);

        ArrayList<MessageResDto> result = new ArrayList<>();

        for(Message message : data){
            result.add(new MessageResDto(message));
        }

        return result;
    }

    public Long sendMessage(Long userId, Long partnerId, MessageReqDto messageReqDto) throws IllegalArgumentException{
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 user id입니다."));
        User partner = userRepository.findById(partnerId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 user id입니다."));
        return messageRepository.save(messageReqDto.toEntity(user, partner)).getId();
    }

    public ArrayList<MessageResDto> getMessagesWithPartner(Long userId, Long partnerId){
        ArrayList<Message> data = messageRepository.findByUserIdAndPartnerId(userId, partnerId);

        ArrayList<MessageResDto> result = new ArrayList<>();
        for(Message message : data){
            result.add(new MessageResDto(message));
        }

        return result;
    }
}
