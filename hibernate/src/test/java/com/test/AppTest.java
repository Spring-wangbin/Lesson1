package com.test;

import com.test.entity.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AppTest {

    Configuration config = null;
    SessionFactory sessionFactory = null;
    Session session = null;
    Transaction transaction = null;

    @Before
    public void before(){
        // 1.创建Configuration对象并加载hibernate.cfg.xml配置文件
        config = new Configuration().configure();
        // 2.获取SessionFactory
        sessionFactory = config.buildSessionFactory();
        // 3.得到一个Session
        session = sessionFactory.openSession();
        // 4.开启事务
        transaction = session.beginTransaction();

        transaction.setTimeout(1234);

        System.out.println("初始化连接配置信息");
    }

    @Test
    public void getById(){
        User u = (User) session.get(User.class,3);
        System.out.println(u);
        session.refresh(u);
        System.out.println(u);
    }

    @Test
    public void queryBySQL(){
        SQLQuery sqlQuery = session.createSQLQuery("select * from t_user");
        List<User> al = sqlQuery.list();//集合返回Object[] 对象
        for (User u : al){
            System.out.println(u);
        }
    }

    @Test
    public void queryList(){
        String hql = " from User";
        Query query = session.createQuery(hql);
        List<User> al = query.list();
        System.out.println(al);
    }

    @Test
    public void updateUser(){
        User user = new User();
        user.setId(1);
        user.setUser_name("zhangsan");
        user.setAge(19);
        user.setSex("男");
        session.update(user);
    }

    @Test
    public void insertUser(){
        User user = new User();
        user.setUser_name("wangwu");
        user.setAge(22);
        user.setSex("女");
        session.save(user);

        user.setUser_name("aaaaa");
    }

    @Test
    public void delUser(){
//        User u = (User) session.get(User.class,1);

        User u = new User();
        u.setId(2);
        session.delete(u);
    }

    @Test
    public void test2(){
        User u = (User) session.get(User.class,3,LockMode.UPGRADE);
        u.setUser_name("aaa");
        session.update(u);
        session.flush();
    }

    @Test
    public void test3(){
        User u = (User) session.get(User.class,3,LockMode.UPGRADE);
        u.setAge(99);
        session.update(u);
        session.flush();
    }

    @After
    public void after(){
        transaction.commit();
        session.close();
        sessionFactory.close();
        System.out.println("连接关闭");
    }
}
