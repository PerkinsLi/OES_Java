package com.perkins.oes.dao;

import com.perkins.oes.entity.User;

public interface UserDao {

    User getUserByName(final String userName);

}
