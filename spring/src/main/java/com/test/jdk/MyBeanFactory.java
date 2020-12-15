package com.test.jdk;

import com.test.jdk.aspect.MyAspect;
import com.test.jdk.dao.CustomerDao;
import com.test.jdk.dao.CustomerDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
    模拟Spring框架的IOC思想，通过调用getBean()方法创建实例
    第 14 行代码创建了 customerDao 实例
    第 24 行代码创建的切面类实例用于调用切面类中相应的方法；
    第 26～34 行就是使用代理类对创建的实例 customerDao 中的方法进行增强的代码，
    其中 Proxy 的 newProxyInstance() 方法：
    第一个参数是当前类的类加载器，
    第二参数是所创建实例的实现类的接口，
    第三个参数就是需要增强的方法。
    在调用 getBean() 方法时，获取的是 CustomerDao 类的代理对象，然后调用了该对象中的方法
 */
public class MyBeanFactory {
    public static CustomerDao getBean(){
        //准备目标类
        final CustomerDao customerDao = new CustomerDaoImpl();
        //创建切面实例
        final MyAspect myAspect = new MyAspect();
        //使用代理类增强
        return (CustomerDao) Proxy.newProxyInstance(
                MyBeanFactory.class.getClassLoader(),
                new Class[]{CustomerDao.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        myAspect.myBefore();
                        Object obj = method.invoke(customerDao,args);
                        myAspect.myAfter();
                        return obj;
                    }
                }
        );
    }
}
