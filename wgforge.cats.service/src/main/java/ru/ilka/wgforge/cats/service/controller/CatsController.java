package ru.ilka.wgforge.cats.service.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ilka.wgforge.cats.service.dto.CatDTO;
import ru.ilka.wgforge.cats.service.entity.enums.CatSortingAttributeEnum;
import ru.ilka.wgforge.cats.service.entity.enums.OrderEnum;
import ru.ilka.wgforge.cats.service.mapper.dataobject.CatDOtoDTOmapper;
import ru.ilka.wgforge.cats.service.mapper.dto.CatDTOtoDOmapper;
import ru.ilka.wgforge.cats.service.service.CatsService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/cats", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CatsController {
    private static Logger logger = LogManager.getLogger(CatsController.class);

    @Autowired
    private CatsService catsService;

    @GetMapping
    public List<CatDTO> getCats(@RequestParam(value = "attribute", required = false) String attribute,
                                @RequestParam(value = "order", required = false) String order,
                                @RequestParam(value = "offset", required = false, defaultValue = "0")
                                @PositiveOrZero(message = "Offset cannot have negative value!") Integer offset,
                                @RequestParam(value = "limit", required = false)
                                @Positive(message = "Limit must have positive value!") Integer limit) {

        return CatDOtoDTOmapper.mapCatsList(catsService.findCats(attribute != null ? CatSortingAttributeEnum.getByName(attribute) : null,
                OrderEnum.getByName(order), offset, limit));
    }

    @PostMapping
    public CatDTO create(@RequestBody @Valid CatDTO catDTO,
                         @RequestParam(value = "updateIfExists", required = false, defaultValue = "false") boolean updateIfExists) {
        return CatDOtoDTOmapper.mapCat(catsService.create(CatDTOtoDOmapper.mapCatDTO(catDTO), updateIfExists));
    }
}
