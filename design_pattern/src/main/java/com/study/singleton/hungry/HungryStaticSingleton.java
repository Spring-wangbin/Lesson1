package com.study.singleton.hungry;

/**
 * 饿汉式单例 静态写法 功能完全一样
 */
public class HungryStaticSingleton {
    private static final HungryStaticSingleton singleton;
    static{
        singleton = new HungryStaticSingleton();
    }
    private HungryStaticSingleton(){}

    public static HungryStaticSingleton getInstance(){
        return singleton;
    }
}
