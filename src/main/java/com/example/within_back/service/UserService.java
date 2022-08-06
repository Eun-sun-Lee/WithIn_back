package com.example.within_back.service;

import com.example.within_back.dto.BoardResDto;
import com.example.within_back.entity.Board;
import com.example.within_back.repository.BoardRepository;
import com.example.within_back.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class UserService {

    @Autowired
    BoardRepository boardRepository;

    public ArrayList<BoardResDto> getMyBoard(Long userId){
        ArrayList<Board> data = boardRepository.findByUserId(userId);
        ArrayList<BoardResDto> result = new ArrayList<>();

        for (Board board : data){
            BoardResDto temp = new BoardResDto(board);
            result.add(temp);
        }
        return result;
    } //내 게시판 조회
}
