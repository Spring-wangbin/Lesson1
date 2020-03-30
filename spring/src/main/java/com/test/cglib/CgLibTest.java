package com.test.cglib;

import com.test.cglib.dao.GoodsDao;

public class CgLibTest {
    public static void main(String[] args) {
        // 从工厂获得指定的内容（相当于spring获得，但此内容是代理对象）
        GoodsDao goodsDao = MyBeanFactory.getBean();
        // 执行方法
        goodsDao.add();
        goodsDao.update();
        goodsDao.delete();
        goodsDao.find();
    }
}
