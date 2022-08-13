package com.example.within_back.controller;

import com.example.within_back.dto.MenuResDto;
import com.example.within_back.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/{date}/{bld}")
    public ArrayList<MenuResDto> getMenu(@PathVariable String date, @PathVariable String bld){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        return menuService.getMenu(localDate, bld);
    }
}


