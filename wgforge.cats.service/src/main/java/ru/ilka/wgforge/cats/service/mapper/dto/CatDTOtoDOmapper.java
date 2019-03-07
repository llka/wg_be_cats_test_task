package ru.ilka.wgforge.cats.service.mapper.dto;

import ru.ilka.wgforge.cats.service.dto.CatDTO;
import ru.ilka.wgforge.cats.service.entity.Cat;
import ru.ilka.wgforge.cats.service.entity.enums.CatColorEnum;

public class CatDTOtoDOmapper {
    public static Cat mapCatDTO(CatDTO dto) {
        if (dto == null) {
            return null;
        }
        Cat cat = new Cat();
        cat.setColor(CatColorEnum.getByName(dto.getColor()));
        cat.setName(dto.getName());
        cat.setTailLength(dto.getTailLength());
        cat.setWhiskersLength(dto.getWhiskersLength());
        return cat;
    }
}
