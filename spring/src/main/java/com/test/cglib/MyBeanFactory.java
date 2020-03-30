package com.test.cglib;

import com.test.cglib.dao.GoodsDao;
import com.test.jdk.aspect.MyAspect;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib代理核心类：Enhancer
 * 在第 28 行代码调用了 Enhancer 类的 setSuperclass() 方法，确定目标对象
 * 第 30 行代码调用 setCallback() 方法添加回调函数；
 * 第 33 行代码的 intercept() 方法相当于 JDK 动态代理方式中的 invoke() 方法，该方法会在目标方法执行的前后，对切面类中的方法进行增强；
 * 第 42～43 行代码调用 Enhancer 类的 create() 方法创建代理类，最后将代理类返回
 */
public class MyBeanFactory {

    public static GoodsDao getBean(){
        //准备目标类
        final GoodsDao goodsDao = new GoodsDao();
        //创建切面实例
        final MyAspect myAspect = new MyAspect();
        //生成代理类，CGLIB在运行时，生成指定对象的子类，增强
        Enhancer enhancer = new Enhancer();
        //确定需要增强的类
        enhancer.setSuperclass(goodsDao.getClass());
        //添加回调函数
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                myAspect.myBefore();
                Object obj = method.invoke(goodsDao,args);
                myAspect.myAfter();
                return obj;
            }
        });

        //创建代理类
        GoodsDao goodsDaoProxy = (GoodsDao) enhancer.create();
        return goodsDaoProxy;
    }
}
