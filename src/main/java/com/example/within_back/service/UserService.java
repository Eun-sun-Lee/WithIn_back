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

import java.util.ArrayList;

public class UserService {

    @Autowired
    BoardRepository boardRepository;
    UserRepository userRepository;
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
    }
}
