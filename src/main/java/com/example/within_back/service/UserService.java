package com.example.within_back.service;

import com.example.within_back.dto.*;
import com.example.within_back.entity.Message;
import com.example.within_back.entity.Post;
import com.example.within_back.entity.User;
import com.example.within_back.repository.*;
import com.example.within_back.entity.Board;
import com.example.within_back.entity.Hobby;
import com.example.within_back.entity.Unit;
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

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UnitRepository unitRepository;

    public ArrayList<PostResDto> getMyPosts(Long userId){
        ArrayList<Post> data = postRepository.findByAuthorId(userId);

        ArrayList<PostResDto> result = new ArrayList<>();

        for(Post post : data){
            int commentCount = commentRepository.countByPostId(post.getId());
            result.add(new PostResDto(post, commentCount));
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
        Long unitId = user.getUnit().getId();

        String army = user.getArmy();
        String position = user.getPosition();
        String mbti = user.getMbti();

        Board boardArmy = boardRepository.findByUnitIdAndCategory(unitId, army);
        Board boardPosition = boardRepository.findByUnitIdAndCategory(unitId, position);
        Board boardMbti = boardRepository.findByUnitIdAndCategory(unitId, mbti);

        ArrayList<Hobby> data = hobbyRepository.findByUserId(userId);
        ArrayList<BoardResDto> result = new ArrayList<>();

        for (Hobby hobby : data) {
            String hobbyCategory = hobby.getCategory();
            Board boardHobby = boardRepository.findByUnitIdAndCategory(unitId,hobbyCategory);
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

        for (Hobby hobby : data) {
            HobbyResDto temp = new HobbyResDto(hobby);
            result.add(temp);
        }

        //user Info
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 USER ID입니다."));
        result.add(new HobbyResDto(user.getArmy(), "army"));
        result.add(new HobbyResDto(user.getPosition(), "position"));
        result.add(new HobbyResDto(user.getMbti(), "mbti"));

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

    public Long createUser(UserReqDto userReqDto) {
        Unit unit = unitRepository.findByUnitName(userReqDto.getArmy());
        User user = userReqDto.toEntity(unit);
        return userRepository.save(user).getId();
    }

    public UnitResDto getUnit(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 user id 입니다."));
        Unit unit = user.getUnit();
        return new UnitResDto(unit);
    }
}
