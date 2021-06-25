package com.test.proxydemo;

public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String msg) {
        System.out.println("方法执行...");
        return "Hello " + msg;
    }
}
