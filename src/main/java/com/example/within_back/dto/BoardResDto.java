package com.example.within_back.dto;

import com.example.within_back.entity.Board;
import lombok.Getter;

@Getter
public class BoardResDto {
    private Long id;
    private String boardName;
    private String category;
    private String explanation;

    public BoardResDto(Board board) {
        this.id = board.getId();
        this.boardName = board.getBoardName();
        this.category = board.getCategory();
        this.explanation = board.getExplanation();
    }
}
