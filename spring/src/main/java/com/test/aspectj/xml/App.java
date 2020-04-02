package com.test.aspectj.xml;

import com.test.aspectj.xml.dao.CustomerDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext_aspectj_xml.xml");
        CustomerDao dao = (CustomerDao) ac.getBean("customerDao");
        dao.add();
    }
}
