package com.test.proxydemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanFactory {
    public static IHelloService getBean(){
        IHelloService service = new HelloServiceImpl();
        MyAspect aspect = new MyAspect();

        return (IHelloService) Proxy.newProxyInstance(
                MyBeanFactory.class.getClassLoader(),
                new Class[]{IHelloService.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        aspect.before();
                        Object obj = method.invoke(service,args);
                        aspect.after();
                        return obj;
                    }
                }
        );
    }
}
