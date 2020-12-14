package com.test.service;

import com.test.entity.User;

public interface UserService {

    public User findUserById(int id);

    public void saveUser(User user);
}
