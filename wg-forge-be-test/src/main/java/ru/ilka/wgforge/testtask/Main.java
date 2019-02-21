package ru.ilka.wgforge.testtask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ilka.wgforge.testtask.service.CatService;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String... args) {
        logger.debug("Hi!");

        CatService catService = new CatService();

        catService.generateCatsColorStatistics();
    }
}
