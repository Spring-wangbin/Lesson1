package com.test.jdk.aspect;

/*
    切面类 MyAspect,
    用于对目标类（CustomerDaoImpl）进行增强
 */
public class MyAspect {
    public void myBefore() {
        System.out.println("方法执行之前");
    }
    public void myAfter() {
        System.out.println("方法执行之后");
    }
}
