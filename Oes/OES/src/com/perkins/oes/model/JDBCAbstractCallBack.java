package com.perkins.oes.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * it can not achieve not need method not achieve
 *
 * @author Perkins.Li
 *
 * @param <T>
 */
public abstract class JDBCAbstractCallBack<T> implements JDBCCallBack<T> {

    @Override
    public T toObject(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public void setParams(PreparedStatement stmt) throws SQLException {

    }

    @Override
    public int getCount(ResultSet rs) throws SQLException {
        return 0;
    }

}
