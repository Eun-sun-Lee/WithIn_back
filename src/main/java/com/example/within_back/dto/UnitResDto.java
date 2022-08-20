package com.example.within_back.dto;

import com.example.within_back.entity.Unit;
import lombok.Getter;

@Getter
public class UnitResDto {
    private Long id;
    private String UnitName;

    public UnitResDto(Unit unit) {
        this.id = unit.getId();
        this.UnitName = unit.getUnitName();
    }
}
