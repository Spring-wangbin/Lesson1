package com.test;

public class Demo {
    static {
        i = 0;

        //非法向前引用
//        System.out.println(i);
    }
    static int i = 1;

    public static void main(String[] args) {
        System.out.println(i);
    }
}
