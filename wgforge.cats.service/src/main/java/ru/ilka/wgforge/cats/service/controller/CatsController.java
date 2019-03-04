package ru.ilka.wgforge.cats.service.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ilka.wgforge.cats.service.entity.Cat;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/cats", produces = MediaType.APPLICATION_JSON_VALUE)
public class CatsController {
    private static Logger logger = LogManager.getLogger(CatsController.class);

    @GetMapping
    public List<Cat> getCats() {
        return new ArrayList<>();
    }
}
