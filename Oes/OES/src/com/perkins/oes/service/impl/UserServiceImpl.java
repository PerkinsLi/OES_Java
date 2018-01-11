package com.perkins.oes.service.impl;

import com.perkins.oes.dao.UserDao;
import com.perkins.oes.entity.User;
import com.perkins.oes.exception.ParameterException;
import com.perkins.oes.exception.ServiceException;
import com.perkins.oes.service.UserService;
import com.perkins.oes.util.StringUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String userName, String password) throws ParameterException, ServiceException {

        ParameterException parameterException = new ParameterException();
        if (StringUtil.IsEmpty(userName)) {
            parameterException.addErrorfields("userName", "userName is required!");
        }

        if (StringUtil.IsEmpty(password)) {
            parameterException.addErrorfields("password", "password is required!");
        }

        if (parameterException.isErrorField()) {
            throw parameterException;
        }

        User user = userDao.getUserByName(userName);
        if (user == null) {
            throw new ServiceException(1000, "userName is error!");
        }

        if (!password.equals(user.getPassword())) {
            throw new ServiceException(1001, "password is error!");
        }

        return user;
    }
}
