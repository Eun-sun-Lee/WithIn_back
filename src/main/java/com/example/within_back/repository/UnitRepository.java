package com.example.within_back.repository;

import com.example.within_back.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

}



