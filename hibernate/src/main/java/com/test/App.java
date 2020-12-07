package com.test;

import com.test.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        // 1.创建Configuration对象并加载hibernate.cfg.xml配置文件
        Configuration config = new Configuration().configure();
        // 2.获取SessionFactory
        SessionFactory sessionFactory = config.buildSessionFactory();
        // 3.得到一个Session
        Session session = sessionFactory.openSession();
        // 4.开启事务
        Transaction transaction = session.beginTransaction();

        // 5.执行持久化操作
        User user = new User();
        user.setUser_name("zhangsan");
        user.setAge(21);
        user.setSex("男");
        // 将对象保存到表中
        session.save(user);

        // 6.提交事务
        transaction.commit();

        // 7.关闭资源
        session.close();
        sessionFactory.close();
    }
}
