package ru.ilka.wgforge.testtask.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ilka.wgforge.testtask.database.ConnectionPool;
import ru.ilka.wgforge.testtask.entity.Cat;
import ru.ilka.wgforge.testtask.entity.CatColorEnum;
import ru.ilka.wgforge.testtask.exception.BackendException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatDAO {
    private static final Logger logger = LogManager.getLogger(CatDAO.class);

    private static final String GET_ALL = "SELECT name, color, tail_length, whiskers_length " +
            " FROM public.cats ";

    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_COLOR = "color";
    private static final String COLUMN_TAIL_LENGTH = "tail_length";
    private static final String COLUMN_WHISKERS_LENGTH = "whiskers_length";

    public List<Cat> getAll() {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Cat> cats = new ArrayList<>();
            while (resultSet.next()) {
                cats.add(buildCat(resultSet));
            }
            return cats;
        } catch (SQLException e) {
            throw new BackendException("Cannot get all cats. " + e);
        }
    }

    private Cat buildCat(ResultSet resultSet) {
        try {
            Cat cat = new Cat();
            cat.setName(resultSet.getString(COLUMN_NAME));
            cat.setColor(CatColorEnum.getByName(resultSet.getString(COLUMN_COLOR)));
            cat.setTailLength(resultSet.getInt(COLUMN_TAIL_LENGTH));
            cat.setWhiskersLength(resultSet.getInt(COLUMN_WHISKERS_LENGTH));
            return cat;
        } catch (SQLException e) {
            throw new BackendException("Error while building company! " + e);
        }
    }
}
