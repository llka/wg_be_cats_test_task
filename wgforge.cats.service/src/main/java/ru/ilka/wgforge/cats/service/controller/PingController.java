package ru.ilka.wgforge.cats.service.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PingController {
    private static Logger logger = LogManager.getLogger(PingController.class);

    @GetMapping(path = "/ping")
    public String ping() {
        logger.debug("Ping!");
        return "Cats Service. Version 0.1";
    }
}
