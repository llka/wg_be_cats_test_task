package ru.ilka.wgforge.testtask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ilka.wgforge.testtask.service.CatService;
import ru.ilka.wgforge.testtask.service.StatisticsService;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String... args) {
        logger.debug("Hi!");

        CatService catService = new CatService();
        StatisticsService statisticsService = new StatisticsService();

        catService.clearCatsColorStatistics();
        catService.generateCatsColorStatistics();
        catService.saveStatistics(statisticsService.calculateCatsStatistics(catService.getAll()));
    }
}
