package com.test.aspectj.annotation;

import com.test.aspectj.annotation.dao.CustomerDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("aspectj_annotation.xml");
        CustomerDao dao = (CustomerDao) ac.getBean("customerDao");
        dao.add();
    }
}
