package com.test;

import static org.junit.Assert.assertTrue;

import com.test.entity.User;
import com.test.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private ApplicationContext context=null;
    private UserService userService=null;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Before
    public void before(){
        System.out.println("初始化...");
        context = new ClassPathXmlApplicationContext("application.xml");
        userService = context.getBean(UserService.class);
    }

    @Test
    public void test(){
        User u= userService.findUserById(5);
        System.out.println(u);
    }

    @Test
    public void save(){
        User u = new User();
        u.setAge(22);
        u.setUser_name("韩梅梅");
        u.setSex("男");
        userService.saveUser(u);
    }
}
