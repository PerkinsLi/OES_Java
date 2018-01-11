package com.perkins.oes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.perkins.oes.exception.DBexception;

public class DButil {

    public static Connection getConnection() {
        String URL = PropertyUtil.getProperties("jdbc.url");
        String USERNAME = PropertyUtil.getProperties("jdbc.user");
        String PASSWORD = PropertyUtil.getProperties("jdbc.password");
        Connection conn = null;
        try {
            Class.forName(PropertyUtil.getProperties("jdbc.driver"));
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBexception();
        }
        return conn;
    }

    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {

        try {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setAutoCommit(Connection conn, boolean autoCommit) {
        try {
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBexception();
        }

    }

    public static void commit(Connection conn) {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBexception();
        }

    }

    public static void rollback(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBexception();
        }

    }
}
