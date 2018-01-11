package com.perkins.oes.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface JDBCCallBack<T> {

    T toObject(ResultSet rs) throws SQLException;

    int getCount(ResultSet rs) throws SQLException;

    void setParams(PreparedStatement stmt) throws SQLException;

}
