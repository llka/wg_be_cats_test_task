package ru.ilka.wgforge.testtask.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ilka.wgforge.testtask.dao.CatDAO;

public class CatService {
    private static final Logger logger = LogManager.getLogger(CatService.class);

    private CatDAO catDAO;

    public CatService() {
        catDAO = new CatDAO();
    }

    public void generateCatsColorStatistics() {
        catDAO.generateCatsStatistics();
    }

}
