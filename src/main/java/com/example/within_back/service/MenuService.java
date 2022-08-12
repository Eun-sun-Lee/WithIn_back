package com.example.within_back.service;

import com.example.within_back.dto.MenuResDto;
import com.example.within_back.entity.Menu;
import com.example.within_back.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public ArrayList<MenuResDto> getMenu(LocalDate date, String bld){
        ArrayList<Menu> data = menuRepository.findByBldAndDate(bld, date);

        ArrayList<MenuResDto> result = new ArrayList<>();

        for(Menu menu : data){
            MenuResDto temp = new MenuResDto(menu);
            result.add(temp);
        }

        return result;
    }
}
