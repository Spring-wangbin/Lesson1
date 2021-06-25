package com.study.singleton.lazy;

/**
 * 优点：线程安全，性能高
 * 缺点：可以被反射破坏，可读性低，不够优雅。。
 */
public class LazySingleton {
    private LazySingleton(){

    }

    private volatile static LazySingleton singleton;

    public static LazySingleton getInstance() throws Exception {
        //检查是否要阻塞
        if(singleton == null){
            synchronized (LazySingleton.class){
                //检查是否要新建实例
                if (singleton == null){
                    singleton = new LazySingleton();
                    //指令重排序问题---volatile
                }
            }
        }
        return singleton;
    }
}
