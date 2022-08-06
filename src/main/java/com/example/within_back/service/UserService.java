package com.example.within_back.service;

import com.example.within_back.dto.BoardResDto;
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

    public ArrayList<BoardResDto> getMyBoard(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 user가 없습니다."));
        String army = user.getArmy();
        String position = user.getPosition();
        String mbti = user.getMbti();
        Board boardArmy = boardRepository.findByCategory(army);
        Board boardPosition = boardRepository.findByCategory(position);
        Board boardMbti = boardRepository.findByCategory(mbti);
        Hobby hobby = hobbyRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 user가 없습니다."));
        String hobbies = hobby.getCategory();
        Board boardHobby = boardRepository.findByCategory(hobbies);

        ArrayList<BoardResDto> result = new ArrayList<>();

        for (Board board : data){
            BoardResDto temp = new BoardResDto(board);
            result.add(temp);
        }
        return result;
    } //내 게시판 조회

//    public ArrayList<BoardResDto> getMyBoard(Long userId){
//        ArrayList<Board> data = boardRepository.findByUserId(userId);
//        ArrayList<BoardResDto> result = new ArrayList<>();
//
//        for (Board board : data){
//            BoardResDto temp = new BoardResDto(board);
//            result.add(temp);
//        }
//        return result;
//    } //내 게시판 조회
}
