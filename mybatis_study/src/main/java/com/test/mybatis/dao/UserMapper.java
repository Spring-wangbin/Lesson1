package com.test.mybatis.dao;

import com.test.mybatis.entity.User;

import java.util.List;

public interface UserMapper {

    public User findUserById(int id);

    public List<User> findAllUser();
}
