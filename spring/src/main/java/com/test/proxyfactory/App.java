package com.test.proxyfactory;

import com.test.proxyfactory.dao.CustomerDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CustomerDao dao = (CustomerDao) ac.getBean("customerDaoProxy");
        dao.add();
    }
}
