package com.test.aspectj.xml.dao;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public void add() {
        System.out.println("添加客户...");
//        int a = 1/0;
    }

    @Override
    public void update() {
        System.out.println("修改客户...");
    }

    @Override
    public void delete() {
        System.out.println("删除客户...");
    }

    @Override
    public void find() {
        System.out.println("查找客户...");
    }
}
