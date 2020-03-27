package com.test.mybatis;

import com.test.mybatis.dao.PersonDao;
import com.test.mybatis.entity.Person;
import com.test.mybatis.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String xmlPath = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
//        PersonDao dao = (PersonDao) ac.getBean("personDao");
//        dao.add();
//        dao.getInfo();

        PersonService service = (PersonService) ac.getBean("personService");

        service.addPerson();

        Person p1 = (Person) ac.getBean("person1");
        Person p2 = (Person) ac.getBean("person2");

        System.out.println(p1);
        System.out.println(p2);
    }
}
