package ru.ilka.wgforge.cats.service.mapper.dataobject;

import ru.ilka.wgforge.cats.service.dto.CatDTO;
import ru.ilka.wgforge.cats.service.entity.Cat;

import java.util.ArrayList;
import java.util.List;

public class CatDOtoDTOmapper {
    public static List<CatDTO> mapCatsList(List<Cat> cats) {
        List<CatDTO> dtoList = new ArrayList<>();
        for (Cat cat : cats) {
            dtoList.add(mapCat(cat));
        }
        return dtoList;
    }

    public static CatDTO mapCat(Cat cat) {
        CatDTO dto = new CatDTO();
        dto.setName(cat.getName());
        dto.setColor(cat.getColor().getName());
        dto.setTailLength(cat.getTailLength());
        dto.setWhiskersLength(cat.getWhiskersLength());
        return dto;
    }
}
