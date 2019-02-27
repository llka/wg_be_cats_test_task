package ru.ilka.wgforge.testtask.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ilka.wgforge.testtask.database.ConnectionPool;
import ru.ilka.wgforge.testtask.exception.BackendException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CatsColorsStatDAO {
    private static final Logger logger = LogManager.getLogger(CatsColorsStatDAO.class);

    private static final String GENERATE_CATS_COLOR_STATISTICS =
            "INSERT INTO public.cat_colors_info (color, count) " +
                    "SELECT  " +
                    "    a.color,  (SELECT COUNT(b.color) FROM public.cats b WHERE b.color = a.color) AS count_ " +
                    "FROM " +
                    "    public.cats a " +
                    "GROUP BY color " +
                    "ORDER BY count_";
    private static final String CLEAR_CATS_COLOR = "DELETE FROM public.cat_colors_info";

    public void generateCatsStatistics() {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GENERATE_CATS_COLOR_STATISTICS)) {
            preparedStatement.execute();
            logger.info("Generated cats colors info!");
        } catch (SQLException e) {
            throw new BackendException("Cannot generate cats statistics! " + e);
        }
    }

    public void clearCatsColorStatistics() {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CLEAR_CATS_COLOR)) {
            preparedStatement.execute();
            logger.info("Cleared cats colors statistics!");
        } catch (SQLException e) {
            throw new BackendException("Cannot clear cats statistics! " + e);
        }
    }

}
