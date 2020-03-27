package com.test.mybatis.dao.impl;

import com.test.mybatis.dao.PersonDao;

public class PersonDaoImpl implements PersonDao {
    @Override
    public void add() {
        System.out.println("add方法。。。");
    }

    @Override
    public void getInfo() {
        System.out.println("getInfo方法执行...");
    }
}
