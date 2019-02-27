package ru.ilka.wgforge.testtask.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ilka.wgforge.testtask.database.ConnectionPool;
import ru.ilka.wgforge.testtask.entity.CatsStatistics;
import ru.ilka.wgforge.testtask.exception.BackendException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CatsStatDAO {
    private static final Logger logger = LogManager.getLogger(CatsStatDAO.class);

    private static final String SAVE = "INSERT INTO public.cats_stat (tail_length_mean, tail_length_median, tail_length_mode, " +
            " whiskers_length_mean, whiskers_length_median, whiskers_length_mode) " +
            " VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT tail_length_mean, tail_length_median, tail_length_mode, " +
            " whiskers_length_mean, whiskers_length_median, whiskers_length_mode " +
            " FROM public.cats_stat ";

    private static final String COLUMN_TAIL_LENGTH_MEAN = "tail_length_mean";
    private static final String COLUMN_TAIL_LENGTH_MEDIAN = "tail_length_median";
    private static final String COLUMN_TAIL_LENGTH_MODE = "tail_length_mode";
    private static final String COLUMN_WHISKERS_LENGTH_MEAN = "whiskers_length_mean";
    private static final String COLUMN_WHISKERS_LENGTH_MEDIAN = "whiskers_length_median";
    private static final String COLUMN_WHISKERS_LENGTH_MODE = "whiskers_length_mode";

    public CatsStatistics save(CatsStatistics catsStatistics) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, catsStatistics.getTailLengthStat().getMean());
            preparedStatement.setDouble(2, catsStatistics.getTailLengthStat().getMedian());
            preparedStatement.setArray(3, connection.createArrayOf("integer", catsStatistics.getTailLengthStat().getMode()));
            preparedStatement.setDouble(4, catsStatistics.getWhiskersLengthStat().getMean());
            preparedStatement.setDouble(5, catsStatistics.getWhiskersLengthStat().getMedian());
            preparedStatement.setArray(6, connection.createArrayOf("integer", catsStatistics.getWhiskersLengthStat().getMode()));
            logger.info("Saved cats statistics!");
            return catsStatistics;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BackendException("Cannot save cats statistics. " + catsStatistics + ". " + e);
        }
    }
}
