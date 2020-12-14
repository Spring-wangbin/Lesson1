package com.test.dao.impl;

import com.test.dao.UserDao;
import com.test.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public User findUserById(int id) {
        String hql = " from User where id = ?";
        Query query = getSession().createQuery(hql).setInteger(0,id);
        List<User> al = query.list();
        return al.get(0);
    }

    @Override
    public void saveUser(User user) {
        getSession().save(user);
    }
}
