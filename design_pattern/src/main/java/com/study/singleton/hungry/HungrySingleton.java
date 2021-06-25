package com.study.singleton.hungry;

/**
 * 饿汉式单例  类加载时创建对象实例
 * 优点：执行效率高，性能高，没有锁
 * 缺点：未使用即加载，可能造成内存浪费
 */
public class HungrySingleton {
    private static final HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return singleton;
    }
}
