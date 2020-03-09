package com.test.mybatis.dao;

import com.test.mybatis.entity.User;

public interface UserMapper {

    public User findUserById(int id);
}
