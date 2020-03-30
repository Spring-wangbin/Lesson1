package com.test.jdk;

import com.test.jdk.dao.CustomerDao;

public class JdkTest {
    public static void main(String[] args) {
        // 从工厂获得指定的内容（相当于spring获得，但此内容时代理对象）
        CustomerDao customerDao = MyBeanFactory.getBean();
        // 执行方法
        customerDao.add();
        customerDao.update();
        customerDao.delete();
        customerDao.find();
    }
}
