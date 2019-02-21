package ru.ilka.wgforge.testtask.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ilka.wgforge.testtask.database.ConnectionPool;
import ru.ilka.wgforge.testtask.exception.BackendException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CatDAO {
    private static final Logger logger = LogManager.getLogger(CatDAO.class);

    private static final String GENERATE_CATS_STATISTICS =
            "INSERT INTO public.cat_colors_info (color, count) " +
            "SELECT  " +
            "    a.color,  (SELECT COUNT(b.color) FROM public.cats b WHERE b.color = a.color) AS count_ " +
            "FROM " +
            "    public.cats a " +
            "GROUP BY color " +
            "ORDER BY count_";

    public void generateCatsStatistics() {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GENERATE_CATS_STATISTICS)) {
            preparedStatement.execute();
            logger.info("Generated cats colors info!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BackendException("Cannot generate cats statistics! " + e);
        }
    }

}
