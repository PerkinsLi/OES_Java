package com.perkins.oes.dao.impl;

import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.perkins.oes.dao.UserDao;
import com.perkins.oes.entity.User;
import com.perkins.oes.util.StringUtil;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    protected final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private final String className = User.class.getName();
    private final String getByname = ".getUserByName";

    @Override
    public User getUserByName(final String userName) {
        if (StringUtil.IsEmpty(userName)) {
            return null;
        }
        User user = null;
        try {
            user = getSqlSession().selectOne(className + getByname, userName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return user;
    }

}
