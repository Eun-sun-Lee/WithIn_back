package com.example.within_back.repository;

import com.example.within_back.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    public ArrayList<Menu> findByBldAndDate(String bld, LocalDate date);
}
