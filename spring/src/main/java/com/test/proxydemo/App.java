package com.test.proxydemo;

public class App {
    public static void main(String[] args) {
        IHelloService service = MyBeanFactory.getBean();
        String ret = service.sayHello("mic");
        System.out.println(ret);
    }
}
