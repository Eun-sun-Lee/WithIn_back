package com.example.within_back.dto;

import com.example.within_back.entity.Menu;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MenuResDto {

    private Long id;
    private String bld;
    private String menu;
    private LocalDate date;

    public MenuResDto(Menu menu) {
        this.id = menu.getId();
        this.bld = menu.getBld();
        this.menu = menu.getMenu();
        this.date = menu.getDate();
    }
}
