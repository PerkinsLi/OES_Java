package com.perkins.oes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.perkins.oes.exception.DBexception;
import com.perkins.oes.util.DButil;

public class JDBCTemplate<T> {
    /**
     * query list
     */
    public List<T> queryList(String sql, JDBCCallBack<T> jdbcCallBack) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<T> data = new ArrayList<T>();
        try {
            con = DButil.getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                T Object = jdbcCallBack.toObject(rs);
                data.add(Object);
            }
        } catch (SQLException e) {
            // print error log
            e.printStackTrace();
            throw new DBexception();
        } finally {
            DButil.close(rs, stmt, con);
        }
        return data;
    }

    /**
     * query one
     */
    public T queryOne(String sql, JDBCCallBack<T> jdbcCallBack) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        T data = null;
        try {
            con = DButil.getConnection();
            stmt = con.prepareStatement(sql);
            // give stmt sql set params
            jdbcCallBack.setParams(stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                data = jdbcCallBack.toObject(rs);
            }
        } catch (SQLException e) {
            // print error log
            e.printStackTrace();
            throw new DBexception();
        } finally {
            DButil.close(rs, stmt, con);
        }
        return data;
    }

    /**
     * update
     *
     */
    public int update(String sql, JDBCCallBack<T> jdbcCallBack) {
        int count = 0;
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DButil.getConnection();
            stmt = con.prepareStatement(sql);
            jdbcCallBack.setParams(stmt);
            count = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(null, stmt, con);
        }

        return count;
    }

    /**
     * insert return id
     */
    public int insert(String sql, JDBCCallBack<T> jdbcCallBack) {
        int id = 0;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = DButil.getConnection();
            stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            jdbcCallBack.setParams(stmt);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(rs, stmt, con);
        }

        return id;
    }

    /**
     * get count
     */
    public int getCount(String sql, JDBCCallBack<T> jdbcCallBack) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = DButil.getConnection();
            stmt = con.prepareStatement(sql);
            // give stmt sql set params
            jdbcCallBack.setParams(stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                count = jdbcCallBack.getCount(rs);
            }
        } catch (SQLException e) {
            // print error log
            e.printStackTrace();
            throw new DBexception();
        } finally {
            DButil.close(rs, stmt, con);
        }
        return count;
    }

}
