package com.perkins.oes.service.impl;

import org.springframework.stereotype.Service;

import com.perkins.oes.Constants;
import com.perkins.oes.dao.UserDao;
import com.perkins.oes.entity.User;
import com.perkins.oes.exception.ParameterException;
import com.perkins.oes.exception.ServiceException;
import com.perkins.oes.service.UserService;
import com.perkins.oes.util.StringUtil;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String userName, String password) throws ParameterException, ServiceException {

        ParameterException parameterException = new ParameterException();
        if (StringUtil.IsEmpty(userName)) {
            parameterException.addErrorfields(Constants.USER_NAME, Constants.USER_NAME_EMPTY);
        }

        if (StringUtil.IsEmpty(password)) {
            parameterException.addErrorfields(Constants.PASSWORD, Constants.PASSWORD_EMPTY);
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
