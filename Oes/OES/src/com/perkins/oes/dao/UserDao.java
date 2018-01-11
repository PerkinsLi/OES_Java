package com.perkins.oes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.perkins.oes.entity.User;
import com.perkins.oes.model.JDBCAbstractCallBack;
import com.perkins.oes.model.JDBCTemplate;

public class UserDao {

    private JDBCTemplate<User> jdbcTemplate;

    public void setJdbcTemplate(JDBCTemplate<User> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUserByName(final String userName) {
        if (userName == null || userName.equals("")) {
            return null;
        }
        return jdbcTemplate.queryOne("SELECT * FROM user WHERE user_name = ?", new JDBCAbstractCallBack<User>() {

            @Override
            public User toObject(ResultSet rs) throws SQLException {
                User user = new User();
                user.setUser_id(rs.getInt("id"));
                user.setUser_name(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                return user;
            }

            @Override
            public void setParams(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, userName);
            }

        });
    }
}
