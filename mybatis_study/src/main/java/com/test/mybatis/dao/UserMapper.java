package com.test.mybatis.dao;

import com.test.mybatis.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    public User findUserById(int id);

    public User findUserByInfo(Map<String,Object> map);

    public List<User> findAllUser();

    public List<Map<String,Object>> getAllUserMap();

    public Map<String,Object> getUserMap(int id);

    public Map<String,Object> execSql(String execSql);
}
