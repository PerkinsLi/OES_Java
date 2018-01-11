package com.perkins.oes.service;

import com.perkins.oes.entity.User;
import com.perkins.oes.exception.ParameterException;
import com.perkins.oes.exception.ServiceException;

public interface UserService {

    public User login(String userName, String password) throws ParameterException, ServiceException;

}
