package com.example.within_back.service;

import com.example.within_back.dto.*;
import com.example.within_back.entity.Message;
import com.example.within_back.entity.Post;
import com.example.within_back.entity.User;
import com.example.within_back.repository.MessageRepository;
import com.example.within_back.entity.Board;
import com.example.within_back.entity.Hobby;
import com.example.within_back.repository.BoardRepository;
import com.example.within_back.repository.HobbyRepository;
import com.example.within_back.repository.PostRepository;
import com.example.within_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    BoardRepository boardRepository;
    
    @Autowired
    HobbyRepository hobbyRepository;
    
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
        ArrayList<Message> data = messageRepository.findMyMessages(userId);

        ArrayList<MessageResDto> result = new ArrayList<>();

        for(Message message : data){
            result.add(new MessageResDto(message));
        }

        return result;
    }

    @Transactional
    public Long sendMessage(Long userId, Long partnerId, MessageReqDto messageReqDto) throws IllegalArgumentException{
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 user id입니다."));
        User partner = userRepository.findById(partnerId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 user id입니다."));
        return messageRepository.save(messageReqDto.toEntity(user, partner)).getId();
    }

    public ArrayList<MessageResDto> getMessagesWithPartner(Long userId, Long partnerId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 user id입니다."));
        ArrayList<Message> data = messageRepository.findByUserIdAndPartnerId(userId, partnerId);

        ArrayList<MessageResDto> result = new ArrayList<>();
        for (Message message : data) {
            result.add(new MessageResDto(message, user));
        }

        return result;
    }

    public ArrayList<BoardResDto> getMyBoard(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 user가 없습니다."));
        String army = user.getArmy();
        String position = user.getPosition();
        String mbti = user.getMbti();
        Board boardArmy = boardRepository.findByCategory(army);
        Board boardPosition = boardRepository.findByCategory(position);
        Board boardMbti = boardRepository.findByCategory(mbti);

        ArrayList<Hobby> data = hobbyRepository.findByUserId(userId);
        ArrayList<BoardResDto> result = new ArrayList<>();


        for (Hobby hobby : data) {
            String hobbyCategory = hobby.getCategory();
            Board boardHobby = boardRepository.findByCategory(hobbyCategory);
            BoardResDto temp = new BoardResDto(boardHobby);
            result.add(temp);
        }
        BoardResDto temp1 = new BoardResDto(boardArmy);
        result.add(temp1);
        BoardResDto temp2 = new BoardResDto(boardPosition);
        result.add(temp2);
        BoardResDto temp3 = new BoardResDto(boardMbti);
        result.add(temp3);
        return result;
    } //내 게시판 불러오기

    public ArrayList<HobbyResDto> getHobby(Long userId) {
        ArrayList<Hobby> data = hobbyRepository.findByUserId(userId);
        ArrayList<HobbyResDto> result = new ArrayList<>();

        result.add(new HobbyResDto("칭찬"));
        result.add(new HobbyResDto("건의"));

        for (Hobby hobby : data) {
            HobbyResDto temp = new HobbyResDto(hobby);
            result.add(temp);
        }

        //user Info
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 USER ID입니다."));
        result.add(new HobbyResDto(user.getArmy()));
        result.add(new HobbyResDto(user.getPosition()));
        result.add(new HobbyResDto(user.getMbti()));

        return result;
    }

    @Transactional
    public Boolean updateHobby(Long userId, HobbyReqDto hobbyReqDto) {
        ArrayList<String> categories = hobbyReqDto.getCategories();
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 USER ID입니다."));

        user.setArmy(hobbyReqDto.getArmy());
        user.setPosition(hobbyReqDto.getPosition());
        user.setMbti(hobbyReqDto.getMbti());
        userRepository.save(user);

        hobbyRepository.deleteByUserId(user.getId());
        for (String category : categories) {
            hobbyRepository.save(hobbyReqDto.toHobbyEntity(user, category));
        }

        return true;
    }

    @Transactional
    public boolean isEmailRepeat(String email) {
        return (userRepository.findByEmail(email) == null) ? false : true;

    }

    @Transactional
    public boolean isNicknameRepeat(String nickname) {
        return (userRepository.findByNickname(nickname) == null) ? false : true;
    }

    public Long getUserId(String uid){
        return userRepository.findByUid(uid).getId();
    }

    public Long createUser(UserReqDto userReqDto){
        User user = userReqDto.toEntity();
        return userRepository.save(user).getId();
    }
}
