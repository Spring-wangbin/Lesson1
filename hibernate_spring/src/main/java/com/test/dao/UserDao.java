package com.test.dao;

import com.test.entity.User;

public interface UserDao {

    public User findUserById(int id);

    public void saveUser(User user);
}
