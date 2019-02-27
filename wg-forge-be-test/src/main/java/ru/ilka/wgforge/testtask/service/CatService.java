package ru.ilka.wgforge.testtask.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ilka.wgforge.testtask.dao.CatDAO;
import ru.ilka.wgforge.testtask.dao.CatsStatDAO;
import ru.ilka.wgforge.testtask.entity.Cat;
import ru.ilka.wgforge.testtask.entity.CatsStatistics;

import java.util.List;

public class CatService {
    private static final Logger logger = LogManager.getLogger(CatService.class);

    private CatDAO catDAO;
    private CatsStatDAO catsStatDAO;

    public CatService() {
        catDAO = new CatDAO();
        catsStatDAO = new CatsStatDAO();
    }

    public void generateCatsColorStatistics() {
        catDAO.generateCatsStatistics();
    }

    public List<Cat> getAll() {
        return catDAO.getAll();
    }

    public void saveStatistics(CatsStatistics catsStatistics) {
        catsStatDAO.save(catsStatistics);
    }

    public void clearCatsColorStatistics() {
        catDAO.clearCatsColorStatistics();
    }

}
