package com.example.within_back.service;

import com.example.within_back.dto.BoardResDto;
import com.example.within_back.dto.HobbyResDto;
import com.example.within_back.entity.Board;
import com.example.within_back.entity.Hobby;
import com.example.within_back.entity.User;
import com.example.within_back.repository.BoardRepository;
import com.example.within_back.repository.HobbyRepository;
import com.example.within_back.repository.PostRepository;
import com.example.within_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.within_back.dto.HobbyReqDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HobbyRepository hobbyRepository;

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
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 USER ID입니다."));
        for (Hobby hobby : data) {
            HobbyResDto temp = new HobbyResDto(hobby, user);
            result.add(temp);
        }
        return result;
    }

    public void updateHobby(Long userId, HobbyReqDto hobbyReqDto) {
        ArrayList<String> categories = hobbyReqDto.getCategories();
        for (String category : categories) {
            hobbyRepository.save(hobbyReqDto.toHobbyEntity(userId, category));
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 USER ID입니다."));
        String category = user.getArmy();
        hobbyRepository.save(hobbyReqDto.toHobbyEntity(userId, category));
        category = user.getPosition();
        hobbyRepository.save(hobbyReqDto.toHobbyEntity(userId, category));
        category = user.getMbti();
        hobbyRepository.save(hobbyReqDto.toHobbyEntity(userId, category));

        String email = user.getEmail();
        String nickname = user.getNickname();
        userRepository.save(hobbyReqDto.toUserEntity(email, nickname));
    }
}
    @Transactional
    public boolean isEmailRepeat(String email) {
        return (userRepository.findByEmail(email) == null) ? false : true;

    }

    @Transactional
    public boolean isNicknameRepeat(String nickname) {
        return (userRepository.findByNickname(nickname) == null) ? false : true;
    }
}
