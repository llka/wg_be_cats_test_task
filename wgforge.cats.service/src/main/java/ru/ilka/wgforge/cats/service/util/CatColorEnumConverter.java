package ru.ilka.wgforge.cats.service.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import ru.ilka.wgforge.cats.service.entity.enums.CatColorEnum;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CatColorEnumConverter extends org.hibernate.type.EnumType {
    private static Logger logger = LogManager.getLogger(CatColorEnumConverter.class);

    private static final int[] SQL_TYPES = new int[]{Types.OTHER};

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        try {
            String value = resultSet.getString(strings[0]);
            return CatColorEnum.getByName(value);
        } catch (Exception exception) {
            logger.error(exception);
        }
        return null;

    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object object, int index, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if (object == null) {
            preparedStatement.setNull(index, Types.OTHER);
        } else {
            CatColorEnum colorEnum = (CatColorEnum) object;
            preparedStatement.setObject(
                    index,
                    colorEnum.getName(),
                    Types.OTHER
            );
        }
    }
}
